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
			Bebida bebida1 = new Bebida("Jugo","Exprimido, fresco y natural, lleno de vitaminas y para mantenerte saludable. Disfruta de su equilibrado sabor dulce y ácido como una opción refrescante para complementar tu comida o empezar tu día!","https://firebasestorage.googleapis.com/v0/b/delicatezza-20007.appspot.com/o/42bb3965-1df3-47cf-a0d4-cb436a9601a2.jpg?alt=media", TipoBebida.AGUA,40,100.0);
			Bebida bebida2 = new Bebida("Birra","¿Estás buscando una cerveza suave y refrescante para acompañar tu comida? Nuestra pilsner fría y fresca que complementará perfectamente tu comida. ¿Queres probar algo un poco más audaz? Nuestra IPA con lúpulo amargo será una opción ideal.","https://firebasestorage.googleapis.com/v0/b/delicatezza-20007.appspot.com/o/5d6a3d6d-fdaf-45ff-81cc-389f6a816971.jpg?alt=media", TipoBebida.ALCOHOLICA,40,350.0);
			bebidaRepositorio.save(bebida1);
			bebidaRepositorio.save(bebida2);
			Comida comida1 = new Comida("Panchito","Un clasico preparado con salchicha de carne en un pan suave y con una variedad de aderezos, como ketchup, mostaza, cebolla picada y queso fundido. ¡Ven y disfruta de esta opción rápida y deliciosa en cualquier momento del día!", TipoComida.PRINCIPAL,250.0,"https://firebasestorage.googleapis.com/v0/b/delicatezza-20007.appspot.com/o/dead24cf-2065-4e92-9ba4-27ed2b9c42e7.jpg?alt=media",true);
			Comida comida2 = new Comida("Burguer","Deliciosa opción para los amantes del queso. Hecha con carne de res fresca, se coloca en un pan suave y se acompaña con doble porción de queso derretido, lechuga, tomate, cebolla y pepinillos. ¡Ven a probarla en nuestro restaurante hoy mismo!", TipoComida.PRINCIPAL,450.0,"https://firebasestorage.googleapis.com/v0/b/delicatezza-20007.appspot.com/o/0e97cb9c-4252-4875-9143-658e39d91ff3.jpg?alt=media",true);
			comidaRepositorio.save(comida1);
			comidaRepositorio.save(comida2);

			Comida postre1 = new Comida ("Helado","Disfruta de nuestro cremoso helado en taza o cono, con una gran variedad de sabores, servido sobre un esponjoso brownie de chocolate. ¡No lo dudes, date el gusto!",TipoComida.POSTRE,500.0,
					"https://firebasestorage.googleapis.com/v0/b/delicatezza-20007.appspot.com/o/6f171418-8f6d-46e5-b15a-d677df87f102.jpg?alt=media",true);
			Comida postre2 = new Comida ("Flan","Elavorado con ingredientes frescos y de alta calidad para garantizar que nuestro flan sea delicioso y satisfactorio. Decorar con un toque de crema batida y dulce de leche!",TipoComida.POSTRE,800.0,
					"https://firebasestorage.googleapis.com/v0/b/delicatezza-20007.appspot.com/o/caa98a39-ea5a-4173-b514-c3c1b66fb500.jpg?alt=media",true);

			comidaRepositorio.save(postre1);
			comidaRepositorio.save(postre2);
			Comida guarnicion1 = new Comida("Papas fritas","Preparadas con las mejores papas seleccionadas y cortadas en tiras uniformes para lograr la textura y sabor perfecto. Disfrutalas acompañadas de alguna de nuestras salsas especiales como kétchup, mayonesa, salsa BBQ, entre otras!",TipoComida.GUARNICION,120.0,"https://firebasestorage.googleapis.com/v0/b/delicatezza-20007.appspot.com/o/3d32de75-2c02-4fcf-ae76-258074e7a137.jpeg?alt=media",true);
			Comida guarnicion2 = new Comida("Aros de cebolla","Acompañados de salsa de aioli o una salsa picante para darle ese toque extra de sabor. Son perfectos para compartir con amigos y familiares mientras esperas la comida principal o simplemente para disfrutar como un aperitivo delicioso.",TipoComida.GUARNICION,180.0,"https://firebasestorage.googleapis.com/v0/b/delicatezza-20007.appspot.com/o/389d1b37-db77-40c5-8b49-4d4c5009455e.jpg?alt=media",true);

			comidaRepositorio.save(guarnicion1);
			comidaRepositorio.save(guarnicion2);

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