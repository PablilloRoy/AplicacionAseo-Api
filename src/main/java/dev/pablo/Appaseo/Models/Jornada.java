package dev.pablo.Appaseo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "jornadas")
public class Jornada {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    private LocalDate fecha;

    private LocalTime horaInicio;
    private LocalTime horaLimiteAceptacion;

    @Enumerated(EnumType.STRING)
    private EstadoJornada estado = EstadoJornada.EN_ESPERA;

    private Boolean aceptacionTardia = false;

    private LocalDateTime fechaInicioReal;


    private Boolean verificadoAdmin = false;


}
