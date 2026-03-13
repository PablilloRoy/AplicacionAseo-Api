package dev.pablo.Appaseo.DTO;

import dev.pablo.Appaseo.Models.Jornada;

import java.time.LocalDate;
import java.time.LocalTime;

public class JornadaDTO {

    private Long id;
    private String equipo;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private String estado;
    private Boolean aceptacionTardia;

    private JornadaDTO convertirDTO(Jornada jornada) {

        return new JornadaDTO(
                jornada.getId(),
                jornada.getEquipo().getNombre(),
                jornada.getFecha(),
                jornada.getHoraInicio(),
                jornada.getEstado().name(),
                jornada.getAceptacionTardia()
        );
    }
    public JornadaDTO() {}

    public JornadaDTO(Long id,
                      String equipo,
                      LocalDate fecha,
                      LocalTime horaInicio,
                      String estado,
                      Boolean aceptacionTardia) {
        this.id = id;
        this.equipo = equipo;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.estado = estado;
        this.aceptacionTardia = aceptacionTardia;
    }

    public Long getId() { return id; }
    public String getEquipo() { return equipo; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public String getEstado() { return estado; }
    public Boolean getAceptacionTardia() { return aceptacionTardia; }

    public void setId(Long id) { this.id = id; }
    public void setEquipo(String equipo) { this.equipo = equipo; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setAceptacionTardia(Boolean aceptacionTardia) { this.aceptacionTardia = aceptacionTardia; }
}