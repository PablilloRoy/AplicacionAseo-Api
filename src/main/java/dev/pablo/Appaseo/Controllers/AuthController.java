package dev.pablo.Appaseo.Controllers;

import dev.pablo.Appaseo.Models.Usuario;
import dev.pablo.Appaseo.Repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String codigo) {

        Usuario usuario = usuarioRepository
                .findByCodigoAcceso(codigo)
                .orElseThrow(() -> new RuntimeException("Código inválido"));

        Map<String, Object> response = new HashMap<>();

        response.put("usuarioId", usuario.getIdUsuario());
        response.put("nombre", usuario.getNombre());
        response.put("rol", usuario.getTipoUsuario().getNombre());

        return ResponseEntity.ok(response);
    }

}