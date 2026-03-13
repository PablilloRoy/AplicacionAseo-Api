package dev.pablo.Appaseo.Service;

public interface NotificationService {

    void notificarUsuario(String pushToken, String titulo, String mensaje);

    static void notificarEquipo(Long equipoId, String titulo, String mensaje) {

    }

}
