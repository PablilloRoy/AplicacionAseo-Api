package dev.pablo.Appaseo.Repository;

import dev.pablo.Appaseo.Models.EstadoJornada;
import dev.pablo.Appaseo.Models.Jornada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JornadaRepository extends JpaRepository<Jornada, Long> {

    List<Jornada> findByFecha(LocalDate fecha);

    List<Jornada> findByEstado(EstadoJornada estado);

    List<Jornada> findByFechaAndEstado(LocalDate fecha, EstadoJornada estado);

    List<Jornada> findByEquipoId(Long equipoId);
    List<Jornada> findByFechaAndEstadoIn(
            LocalDate fecha,
            List<EstadoJornada> estados
    );
}