package com.example.AuthoRasa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AuthoRasaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthoRasaApplication.class, args);
	}
}
