package dev.pablo.Appaseo.Controllers;

import dev.pablo.Appaseo.DTO.DashboardAlumnoDTO;
import dev.pablo.Appaseo.Service.JornadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/app")
public class AppController {

    private final JornadaService jornadaService;

    public AppController(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }

    @GetMapping("/dashboard/{usuarioId}")
    public ResponseEntity<DashboardAlumnoDTO> dashboardAlumno(@PathVariable Long usuarioId) throws Throwable {

        DashboardAlumnoDTO dashboard = jornadaService.obtenerDashboardAlumno(usuarioId);

        return ResponseEntity.ok(dashboard);
    }
}