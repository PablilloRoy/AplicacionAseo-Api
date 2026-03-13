package dev.pablo.Appaseo.Controllers;

import dev.pablo.Appaseo.Models.Equipo;
import dev.pablo.Appaseo.Models.EquipoMiembro;
import dev.pablo.Appaseo.Repository.EquipoMiembroRepository;
import dev.pablo.Appaseo.Repository.EquipoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")

public class EquipoController {

    private final EquipoRepository equipoRepository;
    private final EquipoMiembroRepository miembroRepository;

    public EquipoController(EquipoRepository equipoRepository,
                            EquipoMiembroRepository miembroRepository) {
        this.equipoRepository = equipoRepository;
        this.miembroRepository = miembroRepository;
    }

    @GetMapping
    public List<Equipo> obtenerEquipos() {
        return equipoRepository.findAll();
    }

    @PostMapping
    public Equipo crearEquipo(@RequestBody Equipo equipo) {
        equipo.setActivo(true);
        return equipoRepository.save(equipo);
    }

    @PostMapping("/{equipoId}/miembros")
    public List<EquipoMiembro> agregarMiembros(
            @PathVariable Long equipoId,
            @RequestBody List<String> nombres) {

        Equipo equipo = equipoRepository.findById(equipoId).orElseThrow();

        return nombres.stream().map(nombre -> {

            EquipoMiembro miembro = new EquipoMiembro();
            miembro.setNombre(nombre);
            miembro.setEquipo(equipo);

            return miembroRepository.save(miembro);

        }).toList();
    }
}