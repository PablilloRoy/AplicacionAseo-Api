package dev.pablo.Appaseo.Service.impl;

import dev.pablo.Appaseo.DTO.DashboardAlumnoDTO;
import dev.pablo.Appaseo.DTO.JornadaDTO;
import dev.pablo.Appaseo.Models.*;
import dev.pablo.Appaseo.Repository.*;
import dev.pablo.Appaseo.Service.JornadaService;
import dev.pablo.Appaseo.Service.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class JornadaServiceImpl implements JornadaService {

    private final JornadaRepository jornadaRepository;
    private final EquipoRepository equipoRepository;
    private final ConfirmacionRepository confirmacionRepository;
    private final EquipoMiembroRepository equipoMiembroRepository;
    private final NotificationService notificationService;

    public JornadaServiceImpl(JornadaRepository jornadaRepository,
                              EquipoRepository equipoRepository,
                              ConfirmacionRepository confirmacionRepository,
                              EquipoMiembroRepository equipoMiembroRepository,
                              NotificationService notificationService) {
        this.jornadaRepository = jornadaRepository;
        this.equipoRepository = equipoRepository;
        this.confirmacionRepository = confirmacionRepository;
        this.equipoMiembroRepository = equipoMiembroRepository;
        this.notificationService = notificationService;
    }

    // 🔹 Crear jornada
    @Override
    @Transactional
    public Jornada crearJornada(Long equipoId,
                                LocalDate fecha,
                                LocalTime horaInicio) {

        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        Jornada jornada = new Jornada();
        jornada.setEquipo(equipo);
        jornada.setFecha(fecha);
        jornada.setHoraInicio(horaInicio);
        jornada.setHoraLimiteAceptacion(horaInicio.minusMinutes(10));
        jornada.setEstado(EstadoJornada.EN_ESPERA);

        jornada = jornadaRepository.save(jornada);

        // Crear confirmaciones automáticamente
        List<EquipoMiembro> miembros =
                equipoMiembroRepository.findByEquipoIdAndActivoTrue(equipoId);

        for (EquipoMiembro miembro : miembros) {
            Confirmacion c = new Confirmacion();
            c.setJornada(jornada);
            c.setUsuario((Usuario) miembro.getUsuario());
            confirmacionRepository.save(c);
        }

        return jornada;
    }

    // 🔹 Aceptar jornada
    @Override
    @Transactional
    public void aceptarJornada(Long jornadaId, Long usuarioId) {

        Jornada jornada = jornadaRepository.findById(jornadaId)
                .orElseThrow(() -> new RuntimeException("Jornada no encontrada"));

        Confirmacion confirmacion = confirmacionRepository
                .findByJornadaIdAndUsuarioIdUsuario(jornadaId, usuarioId)
                .orElseThrow(() -> new RuntimeException("Confirmación no encontrada"));

        confirmacion.setAceptado(true);
        confirmacion.setFechaConfirmacion(LocalDateTime.now());

        actualizarEstado(jornada);
    }

    // 🔹 Rechazar jornada
    @Override
    @Transactional
    public void rechazarJornada(Long jornadaId, Long usuarioId) {

        Jornada jornada = jornadaRepository.findById(jornadaId)
                .orElseThrow(() -> new RuntimeException("Jornada no encontrada"));

        Confirmacion confirmacion = confirmacionRepository
                .findByJornadaIdAndUsuarioIdUsuario(jornadaId, usuarioId)
                .orElseThrow(() -> new RuntimeException("Confirmación no encontrada"));

        confirmacion.setAceptado(false);
        confirmacion.setFechaConfirmacion(LocalDateTime.now());

        jornada.setEstado(EstadoJornada.NO_ACEPTADO);
        jornadaRepository.save(jornada);

        NotificationService.notificarEquipo(
                jornada.getEquipo().getId(),
                "❌ Limpieza rechazada",
                "Uno de los integrantes rechazó la limpieza."
        );
    }

    // 🔹 Actualizar estado centralizado
    private void actualizarEstado(Jornada jornada) {

        List<Confirmacion> confirmaciones =
                confirmacionRepository.findByJornadaId(jornada.getId());

        long aceptadas = confirmaciones.stream()
                .filter(c -> Boolean.TRUE.equals(c.getAceptado()))
                .count();

        long rechazadas = confirmaciones.stream()
                .filter(c -> Boolean.FALSE.equals(c.getAceptado()))
                .count();

        if (rechazadas > 0) {

            jornada.setEstado(EstadoJornada.NO_ACEPTADO);

            NotificationService.notificarEquipo(
                    jornada.getEquipo().getId(),
                    "❌ Limpieza rechazada",
                    "Uno de los integrantes rechazó la limpieza."
            );

        } else if (aceptadas == 1) {

            jornada.setEstado(EstadoJornada.ACEPTADA_PARCIAL);

        } else if (aceptadas == confirmaciones.size()) {

            if (LocalTime.now().isAfter(jornada.getHoraLimiteAceptacion())) {
                jornada.setAceptacionTardia(true);

                NotificationService.notificarEquipo(
                        jornada.getEquipo().getId(),
                        "⏰ Aceptación tardía",
                        "La limpieza fue aceptada después del límite."
                );
            }

            jornada.setEstado(EstadoJornada.REALIZANDO);
            jornada.setFechaInicioReal(LocalDateTime.now());
        }

        jornadaRepository.save(jornada);
    }

    // 🔹 Scheduler automático
    @Override
    @Transactional
    public void verificarEstadoAutomatico() {

        List<Jornada> jornadasHoy =
                jornadaRepository.findByFechaAndEstadoIn(
                        LocalDate.now(),
                        Arrays.asList(
                                EstadoJornada.EN_ESPERA,
                                EstadoJornada.ACEPTADA_PARCIAL
                        )
                );

        for (Jornada jornada : jornadasHoy) {

            if (LocalTime.now().isAfter(jornada.getHoraInicio())) {

                jornada.setEstado(EstadoJornada.NO_ACEPTADO);
                jornadaRepository.save(jornada);

                NotificationService.notificarEquipo(
                        jornada.getEquipo().getId(),
                        "⚠ Limpieza no confirmada",
                        "La jornada no fue aceptada a tiempo."
                );
            }
        }
    }

    @Override
    public List<Jornada> obtenerJornadasPorFecha(LocalDate fecha) {
        return jornadaRepository.findByFecha(fecha);
    }

    @Override
    public List<JornadaDTO> obtenerJornadasHoyDTO() {
        return List.of();
    }

    @Override
    public DashboardAlumnoDTO obtenerDashboardAlumno(Long usuarioId) throws Throwable {

        SimpleJpaRepository usuarioRepository = null;
        Usuario usuario = (Usuario) usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        EquipoMiembro equipoMiembro = equipoMiembroRepository
                .findByUsuarioIdUsuarioAndActivoTrue(usuarioId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("El usuario no tiene equipo"));

        Long equipoId = equipoMiembro.getEquipo().getId();

        List<Jornada> jornadas = jornadaRepository.findByEquipoId(equipoId);

        Jornada proxima = jornadas.stream()
                .filter(j -> j.getFecha().isAfter(LocalDate.now()) || j.getFecha().isEqual(LocalDate.now()))
                .sorted(Comparator.comparing(Jornada::getFecha))
                .findFirst()
                .orElse(null);

        JornadaDTO jornadaDTO = null;

        if (proxima != null) {
            jornadaDTO = new JornadaDTO(
                    proxima.getId(),
                    proxima.getEquipo().getNombre(),
                    proxima.getFecha(),
                    proxima.getHoraInicio(),
                    proxima.getEstado().name(),
                    proxima.getAceptacionTardia()
            );
        }

        return new DashboardAlumnoDTO(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                equipoMiembro.getEquipo().getNombre(),
                jornadaDTO
        );
    }
}