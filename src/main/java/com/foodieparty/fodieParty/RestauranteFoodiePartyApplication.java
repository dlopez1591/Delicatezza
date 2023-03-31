package com.foodieparty.fodieParty;

import com.foodieparty.fodieParty.models.*;
import com.foodieparty.fodieParty.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RestauranteFoodiePartyApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(RestauranteFoodiePartyApplication.class, args);
		System.out.println("Exitos!!");
	}

	@Bean
	public CommandLineRunner initData(
			UsuarioRepositorio usuarioRepositorio,
			BebidaRepositorio bebidaRepositorio,
			ComidaRepositorio comidaRepositorio,
			ReservaRepositorio reservaRepositorio,
			CapacidadRepositorio capacidadRepositorio
	) {
		return (args) -> {
//			public Usuario(String nombre, String apellido, String email, String contraseña, String telefono)

			Usuario pepito = new Usuario("pepito","pepe","pepe@pepe.com", passwordEncoder.encode("pepe"),"1111");
			usuarioRepositorio.save(pepito);
			Bebida bebida1 = new Bebida("agua","botellita de agua","urlImagen", TipoBebida.AGUA,40,100.0);
			Bebida bebida2 = new Bebida("birra","vaso de birra","urlImagen", TipoBebida.ALCOHOLICA,40,350.0);
			bebidaRepositorio.save(bebida1);
			bebidaRepositorio.save(bebida2);
			Comida comida1 = new Comida("panchito","ia tu sabe", TipoComida.PRINCIPAL,250.0,"urlImagen",true);
			Comida comida2 = new Comida("burguer","con doble soque", TipoComida.PRINCIPAL,450.0,"urlImagen",true);
			comidaRepositorio.save(comida1);
			comidaRepositorio.save(comida2);

			//HARDCODE RESERVAS Y CAPACIDAD
			Capacidad capacidadRestoran = new Capacidad((byte)10,1500.0);
			Reserva reserva1 = new Reserva();
			Reserva reserva2 = new Reserva();
			Reserva reserva3 = new Reserva();
			Reserva reserva4 = new Reserva();
			Reserva reserva5 = new Reserva();
			Reserva reserva6 = new Reserva();
			reserva1.setFecha(LocalDate.of(2023,03,29));
			reserva2.setFecha(LocalDate.of(2023,03,29));
			reserva3.setFecha(LocalDate.of(2023,03,30));
			reserva4.setFecha(LocalDate.of(2023,03,30));
			reserva5.setFecha(LocalDate.of(2023,03,30));
			reserva6.setFecha(LocalDate.of(2023,03,30));
			reserva1.setCantidad_De_Personas((byte)2);
			reserva2.setCantidad_De_Personas((byte)2);
			reserva3.setCantidad_De_Personas((byte)2);
			reserva4.setCantidad_De_Personas((byte)2);
			reserva5.setCantidad_De_Personas((byte)2);
			reserva6.setCantidad_De_Personas((byte)2);
			capacidadRepositorio.save(capacidadRestoran);
			reservaRepositorio.saveAll(List.of(reserva1,reserva2,reserva3,reserva4,reserva5,reserva6));

		};
	}

}
