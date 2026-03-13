package dev.pablo.Appaseo.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.*;

@Entity
@Data
@Table(name = "evidencias")
public class Evidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "jornada_id", nullable = false)
    private Jornada jornada;

    @Column(name = "url_imagen", nullable = false)
    private String urlImagen;

    @Column(name = "fecha_subida")
    private LocalDateTime fechaSubida = LocalDateTime.now();


    // Getters & Setters
}