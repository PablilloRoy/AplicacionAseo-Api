package dev.pablo.Appaseo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling


@SpringBootApplication
public class AppaseoApplication {

	public static void main(String[] args) {

		SpringApplication.run(AppaseoApplication.class, args);
	}

}
