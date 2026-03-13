package dev.pablo.Appaseo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tareaDefault;

    private Boolean activo = true;

    @OneToMany(mappedBy = "equipo")
    @JsonIgnore
    private List<Usuario> usuarios;

    public Long getIdEquipo() {
        return getId();
    }

    public Collection<Object> getUsuarios() {
        return getUsuarios();
    }
}
