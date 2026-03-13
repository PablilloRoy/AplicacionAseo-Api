package dev.pablo.Appaseo.Service.impl;

import dev.pablo.Appaseo.Repository.EquipoMiembroRepository;
import dev.pablo.Appaseo.Service.NotificationService;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final EquipoMiembroRepository equipoMiembroRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public NotificationServiceImpl(EquipoMiembroRepository equipoMiembroRepository) {
        this.equipoMiembroRepository = equipoMiembroRepository;
    }

    @Override
    public void notificarUsuario(String pushToken, String titulo, String mensaje) {

        if (pushToken == null || !pushToken.startsWith("ExponentPushToken")) {
            return;
        }

        String expoUrl = "https://exp.host/--/api/v2/push/send";

        Map<String, Object> body = new HashMap<>();
        body.put("to", pushToken);
        body.put("title", titulo);
        body.put("body", mensaje);
        body.put("sound", "default");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        restTemplate.postForEntity(expoUrl, request, String.class);
    }

    public void notificarEquipo(Long equipoId, String titulo, String mensaje) {

        var miembros =
                equipoMiembroRepository.findByEquipoIdAndActivoTrue(equipoId);

        miembros.forEach(miembro -> {
            String token = String.valueOf(miembro.getUsuario().hashCode());
            notificarUsuario(token, titulo, mensaje);
        });
    }
}