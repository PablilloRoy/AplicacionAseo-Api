package dev.pablo.Appaseo.DTO;

public class DashboardAlumnoDTO {

    private Long usuarioId;
    private String nombre;
    private String equipo;
    private JornadaDTO proximaJornada;

    public DashboardAlumnoDTO() {}

    public DashboardAlumnoDTO(Long usuarioId, String nombre, String equipo, JornadaDTO proximaJornada) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.equipo = equipo;
        this.proximaJornada = proximaJornada;
    }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEquipo() { return equipo; }
    public void setEquipo(String equipo) { this.equipo = equipo; }

    public JornadaDTO getProximaJornada() { return proximaJornada; }
    public void setProximaJornada(JornadaDTO proximaJornada) { this.proximaJornada = proximaJornada; }
}