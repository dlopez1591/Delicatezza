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

			Usuario cosme = new Usuario("cosme","fulanito","cosme@fulanito.com", passwordEncoder.encode("cosme") ,"221314242");
			usuarioRepositorio.save(cosme);
			Usuario pepito = new Usuario("pepito","pepe","pepe@pepe.com",passwordEncoder.encode("pepe"),"1111");

			usuarioRepositorio.save(pepito);
			Usuario juan = new Usuario("juan", "cardenas", "juancardenas123@hotmail.com", passwordEncoder.encode("juan123"), "123456");
			usuarioRepositorio.save(juan);
			Usuario daniel = new Usuario("daniel", "lopez", "dlopez1591@gmail.com", passwordEncoder.encode("daniel123"), "123456");
			usuarioRepositorio.save(daniel);
			Bebida bebida1 = new Bebida("Jugo","Jugo de naranja natural","https://firebasestorage.googleapis.com/v0/b/delicatezza-20007.appspot.com/o/42bb3965-1df3-47cf-a0d4-cb436a9601a2.jpg?alt=media", TipoBebida.AGUA,40,100.0);
			Bebida bebida2 = new Bebida("Birra","vaso de birra ipa","https://firebasestorage.googleapis.com/v0/b/delicatezza-20007.appspot.com/o/9b789c94-465b-48c5-b9d3-7fb22d24c3f0.jpg?alt=media", TipoBebida.ALCOHOLICA,40,350.0);
			bebidaRepositorio.save(bebida1);
			bebidaRepositorio.save(bebida2);
			Comida comida1 = new Comida("Panchito","Panchito con adereso y papitas", TipoComida.PRINCIPAL,250.0,"https://firebasestorage.googleapis.com/v0/b/delicatezza-20007.appspot.com/o/dead24cf-2065-4e92-9ba4-27ed2b9c42e7.jpg?alt=media",true);
			Comida comida2 = new Comida("Burguer","Hamburguesa Doble Queso", TipoComida.PRINCIPAL,450.0,"https://firebasestorage.googleapis.com/v0/b/delicatezza-20007.appspot.com/o/0e97cb9c-4252-4875-9143-658e39d91ff3.jpg?alt=media",true);
			comidaRepositorio.save(comida1);
			comidaRepositorio.save(comida2);

			Comida postre1 = new Comida ("Helado","bochita",TipoComida.POSTRE,500.0,
					"https://firebasestorage.googleapis.com/v0/b/delicatezza-20007.appspot.com/o/6f171418-8f6d-46e5-b15a-d677df87f102.jpg?alt=media",true);
			Comida postre2 = new Comida ("Flan","un flansito",TipoComida.POSTRE,800.0,
					"https://firebasestorage.googleapis.com/v0/b/delicatezza-20007.appspot.com/o/caa98a39-ea5a-4173-b514-c3c1b66fb500.jpg?alt=media",true);

			comidaRepositorio.save(postre1);
			comidaRepositorio.save(postre2);

			//HARDCODE RESERVAS Y CAPACIDAD
			Capacidad capacidadRestoran = new Capacidad((byte)10,1500.0);
			Reserva reserva1 = new Reserva();
			Reserva reserva2 = new Reserva();
			Reserva reserva3 = new Reserva();
			Reserva reserva4 = new Reserva();
			Reserva reserva5 = new Reserva();
			Reserva reserva6 = new Reserva();

			// nueva reserva
			Reserva reserva7 = new Reserva(LocalDate.now(),(byte)5, false );

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

			reserva1.setEstado(true);
			reserva2.setEstado(true);
			reserva3.setEstado(true);
			reserva4.setEstado(true);
			reserva5.setEstado(false);
			reserva6.setEstado(true);

			pepito.agregarReserva(reserva1);
			pepito.agregarReserva(reserva2);
			pepito.agregarReserva(reserva3);
			pepito.agregarReserva(reserva4);
			cosme.agregarReserva(reserva5);
			cosme.agregarReserva(reserva6);
			pepito.agregarReserva(reserva7);
			capacidadRepositorio.save(capacidadRestoran);
			reservaRepositorio.saveAll(List.of(reserva1,reserva2,reserva3,reserva4,reserva5,reserva6));

		};
	}

}