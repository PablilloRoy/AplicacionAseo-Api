package dev.pablo.Appaseo.Service;

import dev.pablo.Appaseo.DTO.DashboardAlumnoDTO;
import dev.pablo.Appaseo.DTO.JornadaDTO;
import dev.pablo.Appaseo.Models.Jornada;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface JornadaService {

    Jornada crearJornada(Long equipoId,
                         LocalDate fecha,
                         LocalTime horaInicio);

    void aceptarJornada(Long jornadaId, Long usuarioId);

    void rechazarJornada(Long jornadaId, Long usuarioId);

    void verificarEstadoAutomatico();

    List<Jornada> obtenerJornadasPorFecha(LocalDate fecha);

    List<JornadaDTO> obtenerJornadasHoyDTO();

    DashboardAlumnoDTO obtenerDashboardAlumno(Long usuarioId) throws Throwable;
}