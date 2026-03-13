package dev.pablo.Appaseo.Service;

import dev.pablo.Appaseo.DTO.EquipoDTO;
import dev.pablo.Appaseo.Models.RolLimpieza;
import dev.pablo.Appaseo.Repository.RolLimpiezaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AseoService {

    @Autowired
    private RolLimpiezaRepository rolRepository;

    public List<EquipoDTO> getPanelData() {
        List<RolLimpieza> roles = rolRepository.findAllWithEquipos();

        return roles.stream().map(rol -> {
            EquipoDTO dto = new EquipoDTO();
            dto.setId(rol.getEquipo().getIdEquipo());
            dto.setNombre(rol.getEquipo().getNombre());
            dto.setTarea(rol.getEquipo().getTareaDefault());
            dto.setStatus(rol.getEstatus());
            dto.setFecha(rol.getFechaProgramada().toString());

            // Mapeamos los integrantes del equipo a una lista de Strings
            List<String> nombres = rol.getEquipo().getUsuarios().stream()
                    .map(u -> u.getClass() + " " + u.getClass())
                    .collect(Collectors.toList());

            dto.setMiembros(nombres);
            return dto;
        }).collect(Collectors.toList());
    }
}