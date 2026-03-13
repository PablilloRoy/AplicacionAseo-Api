package dev.pablo.Appaseo.Models;

import jakarta.persistence.*;
import lombok.Data;


import jakarta.persistence.*;

@Entity
@Data
@Table(name = "tipos_usuarios")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Long idTipo;

    @Column(nullable = false, unique = true)
    private String nombre;


}
