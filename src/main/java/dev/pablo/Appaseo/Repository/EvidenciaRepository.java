package dev.pablo.Appaseo.Repository;

import dev.pablo.Appaseo.Models.Evidencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvidenciaRepository extends JpaRepository<Evidencia, Long> {

    List<Evidencia> findByJornadaId(Long jornadaId);
}