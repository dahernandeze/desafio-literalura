package com.alura.desafio_literalura;

import com.alura.desafio_literalura.principal.AplicacionPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private AplicacionPrincipal aplicacionPrincipal;

	public static void main(String[] args) {
		SpringApplication.run(DesafioLiteraluraApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		aplicacionPrincipal.ejecutar();
	}
}
