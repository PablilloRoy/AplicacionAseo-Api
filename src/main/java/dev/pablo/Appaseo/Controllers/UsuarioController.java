package dev.pablo.Appaseo.Controllers;

import dev.pablo.Appaseo.Models.Usuario;
import dev.pablo.Appaseo.Repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {

        List<Usuario> usuarios = usuarioRepository.findByActivoTrue();

        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/{usuarioId}/push-token")
    public ResponseEntity<?> guardarPushToken(
            @PathVariable Long usuarioId,
            @RequestParam String token) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setPushToken(token);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Push token guardado");
    }
}