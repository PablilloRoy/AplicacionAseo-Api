package dev.pablo.Appaseo.Controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HomeController {

    @GetMapping("/hola")
    public String decirHola() {
        return "Hola Mundo";
    }
}
