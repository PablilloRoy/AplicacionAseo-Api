package dev.pablo.Appaseo.Repository;


import dev.pablo.Appaseo.Models.TipoUsuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {

    Optional<TipoUsuario> findByNombre(String nombre);
}
