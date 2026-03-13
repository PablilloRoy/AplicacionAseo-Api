package dev.pablo.Appaseo.Controllers;

import dev.pablo.Appaseo.DTO.EquipoDTO;
import dev.pablo.Appaseo.Service.AseoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/aseo")
@CrossOrigin(origins = "*") // Para que React Native no tenga bloqueos de CORS
public class AseoController {

    @Autowired
    private AseoService aseoService;

    @GetMapping("/panel")
    public ResponseEntity<List<EquipoDTO>> obtenerPanel() {
        return ResponseEntity.ok(aseoService.getPanelData());
    }

}