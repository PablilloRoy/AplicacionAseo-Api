package dev.pablo.Appaseo.Repository;



import dev.pablo.Appaseo.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);

    List<Usuario> findByActivoTrue();

    Optional<Usuario> findByCodigoAcceso(String codigoAcceso);;
}
