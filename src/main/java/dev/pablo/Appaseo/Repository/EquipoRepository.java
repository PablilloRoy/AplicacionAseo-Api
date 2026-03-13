package dev.pablo.Appaseo.Repository;

import dev.pablo.Appaseo.Models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findByActivoTrue();
}
