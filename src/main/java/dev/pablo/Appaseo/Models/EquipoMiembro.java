package dev.pablo.Appaseo.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "equipo_miembros")
public class EquipoMiembro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Object getUsuario() {
        return  getUsuario();
    }

}