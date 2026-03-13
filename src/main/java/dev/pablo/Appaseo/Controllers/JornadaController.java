package dev.pablo.Appaseo.Controllers;

import dev.pablo.Appaseo.DTO.JornadaDTO;
import dev.pablo.Appaseo.Models.Jornada;
import dev.pablo.Appaseo.Repository.JornadaRepository;
import dev.pablo.Appaseo.Service.JornadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/jornadas")
public class JornadaController {

    private final JornadaService jornadaService;

    public JornadaController(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }

    // Crear jornada
    @PostMapping("/crear")
    public ResponseEntity<Jornada> crearJornada(
            @RequestParam Long equipoId,
            @RequestParam String fecha,
            @RequestParam String horaInicio) {

        Jornada jornada = jornadaService.crearJornada(
                equipoId,
                LocalDate.parse(fecha),
                LocalTime.parse(horaInicio)
        );

        return ResponseEntity.ok(jornada);
    }

    // Aceptar jornada
    @PostMapping("/{jornadaId}/aceptar/{usuarioId}")
    public ResponseEntity<String> aceptarJornada(
            @PathVariable Long jornadaId,
            @PathVariable Long usuarioId) {

        jornadaService.aceptarJornada(jornadaId, usuarioId);

        return ResponseEntity.ok("Aceptado");
    }

    // Rechazar jornada
    @PostMapping("/{jornadaId}/rechazar/{usuarioId}")
    public ResponseEntity<String> rechazarJornada(
            @PathVariable Long jornadaId,
            @PathVariable Long usuarioId) {

        jornadaService.rechazarJornada(jornadaId, usuarioId);

        return ResponseEntity.ok("Rechazado");
    }

    @GetMapping("/hoy")
    public ResponseEntity<List<JornadaDTO>> obtenerHoy() {

        List<JornadaDTO> jornadas = jornadaService.obtenerJornadasHoyDTO();

        return ResponseEntity.ok(jornadas);
    }

    // Jornadas por fecha
    @GetMapping("/fecha")
    public ResponseEntity<List<Jornada>> obtenerPorFecha(
            @RequestParam String fecha) {

        return ResponseEntity.ok(
                jornadaService.obtenerJornadasPorFecha(LocalDate.parse(fecha))
        );
    }

    @GetMapping("/equipo/{equipoId}")
    public ResponseEntity<List<Jornada>> jornadasPorEquipo(
            @PathVariable Long equipoId) {

        JornadaRepository jornadaRepository = null;
        return ResponseEntity.ok(
                jornadaRepository.findByEquipoId(equipoId)
        );
    }
}