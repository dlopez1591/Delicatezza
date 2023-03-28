package com.foodieparty.fodieParty;

import com.foodieparty.fodieParty.models.*;
import com.foodieparty.fodieParty.repositories.BebidaPedidoRepositorio;
import com.foodieparty.fodieParty.repositories.BebidaRepositorio;
import com.foodieparty.fodieParty.repositories.ComidaRepositorio;
import com.foodieparty.fodieParty.repositories.UsuarioRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class RestauranteFoodiePartyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteFoodiePartyApplication.class, args);
		System.out.println("Exitos!!");
	}

	@Bean
	public CommandLineRunner initData(
			UsuarioRepositorio usuarioRepositorio,
			BebidaRepositorio bebidaRepositorio,
			ComidaRepositorio comidaRepositorio
	) {
		return (args) -> {
//			public Usuario(String nombre, String apellido, String email, String contraseña, String telefono)
			Usuario pepito = new Usuario("pepito","pepe","pepe@pepe.com","pepe","1111");
			usuarioRepositorio.save(pepito);
			Bebida bebida1 = new Bebida("agua","botellita de agua","urlImagen", TipoBebida.AGUA,40,100.0);
			Bebida bebida2 = new Bebida("birra","vaso de birra","urlImagen", TipoBebida.ALCOHOLICA,40,350.0);
			bebidaRepositorio.save(bebida1);
			bebidaRepositorio.save(bebida2);
			Comida comida1 = new Comida("panchito","ia tu sabe", TipoComida.PRINCIPAL,250.0,"urlImagen",true);
			Comida comida2 = new Comida("burguer","con doble soque", TipoComida.PRINCIPAL,450.0,"urlImagen",true);
			comidaRepositorio.save(comida1);
			comidaRepositorio.save(comida2);
		};
	}

}
