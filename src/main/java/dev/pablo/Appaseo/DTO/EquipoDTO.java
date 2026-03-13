package dev.pablo.Appaseo.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class EquipoDTO {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String tarea;
    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    private String fecha;
    @Getter
    @Setter
    private List<String> miembros;

}