package dev.pablo.Appaseo.Repository;

import dev.pablo.Appaseo.Models.Ausencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AusenciaRepository extends JpaRepository<Ausencia, Long> {

    List<Ausencia> findByFecha(LocalDate fecha);

    List<Ausencia> findByUsuarioIdUsuarioAndFecha(Long usuarioId, LocalDate fecha);
}
