package dev.pablo.Appaseo.Controllers;

import dev.pablo.Appaseo.Models.Evidencia;
import dev.pablo.Appaseo.Models.Jornada;
import dev.pablo.Appaseo.Repository.EvidenciaRepository;
import dev.pablo.Appaseo.Repository.JornadaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/evidencias")
public class EvidenciaController {

    private final EvidenciaRepository evidenciaRepository;
    private final JornadaRepository jornadaRepository;

    private final String uploadDir = "uploads/evidencias/";

    public EvidenciaController(EvidenciaRepository evidenciaRepository,
                               JornadaRepository jornadaRepository) {
        this.evidenciaRepository = evidenciaRepository;
        this.jornadaRepository = jornadaRepository;
    }

    @PostMapping("/subir")
    public ResponseEntity<?> subirEvidencia(
            @RequestParam Long jornadaId,
            @RequestParam MultipartFile imagen) {

        try {

            Jornada jornada = jornadaRepository.findById(jornadaId)
                    .orElseThrow(() -> new RuntimeException("Jornada no encontrada"));

            // Crear carpeta si no existe
            File carpeta = new File(uploadDir);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            // Nombre único
            String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();

            File destino = new File(uploadDir + nombreArchivo);

            imagen.transferTo(destino);

            Evidencia evidencia = new Evidencia();
            evidencia.setJornada(jornada);
            evidencia.setUrlImagen("/uploads/evidencias/" + nombreArchivo);
            evidencia.setFechaSubida(LocalDateTime.now());

            evidenciaRepository.save(evidencia);

            return ResponseEntity.ok("Evidencia subida correctamente");

        } catch (IOException e) {

            return ResponseEntity.internalServerError()
                    .body("Error al guardar la imagen");
        }
    }

    @GetMapping("/jornada/{jornadaId}")
    public ResponseEntity<?> obtenerEvidencias(@PathVariable Long jornadaId) {

        return ResponseEntity.ok(
                evidenciaRepository.findByJornadaId(jornadaId)
        );
    }
}