package dev.pablo.Appaseo.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Rol_Limpieza")
public class RolLimpieza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdRol;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdEquipo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Equipo IdEquipo;

    private String FechaProgramada;
    private String Estatus;
    private String EvidenciaRuta;
    private LocalDateTime FechaEvidencia;
    private boolean VerificadoJefe;


    public Equipo getEquipo() {
        return null;
    }
}
