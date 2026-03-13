package dev.pablo.Appaseo.Repository;

import dev.pablo.Appaseo.Models.EquipoMiembro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoMiembroRepository extends JpaRepository<EquipoMiembro, Long> {

    List<EquipoMiembro> findByEquipoIdAndActivoTrue(Long equipoId);

    List<EquipoMiembro> findByUsuarioIdUsuarioAndActivoTrue(Long usuarioId);

    List<EquipoMiembro> findByEquipoId(Long equipoId);

}
