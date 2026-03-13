package dev.pablo.Appaseo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;
    private String apellidos;

    @Column(unique = true)
    private String correo;

    @JsonIgnore
    private String passwordHash;

    private Boolean activo = true;

    private String pushToken;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private TipoUsuario tipoUsuario;

    @Column(name = "codigo_acceso", unique = true)
    private String codigoAcceso;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    @JsonIgnore
    private Equipo equipo;

}