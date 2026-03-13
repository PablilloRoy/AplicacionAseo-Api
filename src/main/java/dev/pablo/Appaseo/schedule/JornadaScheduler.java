package dev.pablo.Appaseo.schedule;

import dev.pablo.Appaseo.Service.JornadaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JornadaScheduler {

    private final JornadaService jornadaService;

    public JornadaScheduler(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }

    // Se ejecuta cada 1 minuto
    @Scheduled(cron = "0 * * * * *")
    public void verificarJornadas() {

        System.out.println("🔄 Verificando jornadas automáticamente...");

        jornadaService.verificarEstadoAutomatico();
    }
}