package dev.pablo.Appaseo.Repository;

import dev.pablo.Appaseo.Models.RolLimpieza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolLimpiezaRepository extends JpaRepository<RolLimpieza, Long> {

    // query to fetch all RolLimpieza with their associated Equipos
    @Query("SELECT r FROM RolLimpieza r JOIN FETCH r.IdEquipo")
    List<RolLimpieza> findAllWithEquipos();
}