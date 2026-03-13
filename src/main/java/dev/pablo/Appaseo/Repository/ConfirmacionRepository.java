package dev.pablo.Appaseo.Repository;

import dev.pablo.Appaseo.Models.Confirmacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConfirmacionRepository extends JpaRepository<Confirmacion, Long> {

    List<Confirmacion> findByJornadaId(Long jornadaId);

    Optional<Confirmacion> findByJornadaIdAndUsuarioIdUsuario(Long jornadaId, Long usuarioId);
}
