package com.example.demo;

import com.example.demo.repositoryu.RepositoryAutores;
import com.example.demo.repositoryu.RepositoryLibros;
import com.example.demo.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaAppSegundoIntentoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaAppSegundoIntentoApplication.class, args);
	}
	@Autowired
	private RepositoryLibros repositoryLibros;

	@Autowired
	RepositoryAutores repositoryAutores;

	//Método que se ejecuta al iniciar la aplicación.
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositoryLibros,repositoryAutores);
		principal.menuDeUsuario();
	}
}
