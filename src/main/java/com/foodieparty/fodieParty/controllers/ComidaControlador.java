package com.foodieparty.fodieParty.controllers;
import com.foodieparty.fodieParty.dtos.ComidaDTO;
import com.foodieparty.fodieParty.dtos.NuevaComidaDTO;
import com.foodieparty.fodieParty.models.Bebida;
import com.foodieparty.fodieParty.models.Comida;
import com.foodieparty.fodieParty.models.TipoBebida;
import com.foodieparty.fodieParty.models.TipoComida;
import com.foodieparty.fodieParty.repositories.ComidaRepositorio;
import com.foodieparty.fodieParty.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ComidaControlador {
    @Autowired
    private ComidaRepositorio comidaRepositorio;

    @Autowired
    private FileService fileService;

    @GetMapping("/comidas")
    public List<ComidaDTO> getComidas(){
        return comidaRepositorio.findAll().stream().map(c->new ComidaDTO(c)).collect(Collectors.toList());
    }

    @GetMapping("/comidas/{id}")
    public Optional<ComidaDTO> getComidaPorId(@PathVariable Long id){
        return  comidaRepositorio.findById(id).map(c->new ComidaDTO(c));
    }

    @PostMapping("/crear/comida")
    public ResponseEntity<Object> crearComida(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("tipoComida") TipoComida tipoComida,
            @RequestParam("disponibilidad") Boolean disponibilidad,
            @RequestParam("precio") Double precio
    ){
        if(nombre==null){
            return new ResponseEntity<>("Nombre vacio (null)", HttpStatus.FORBIDDEN);
        }
        if(descripcion==null){
            return new ResponseEntity<>("Descripcion vacia (null)", HttpStatus.FORBIDDEN);
        }
        if(imagen==null){
            return new ResponseEntity<>("Imagen vacia (null)", HttpStatus.FORBIDDEN);
        }
        if(tipoComida==null){
            return new ResponseEntity<>("Tipo vacio (null)", HttpStatus.FORBIDDEN);
        }
        if(disponibilidad==null){
            return new ResponseEntity<>("Disponibilidad vacia (null)", HttpStatus.FORBIDDEN);
        }
        if(precio<=0){
            return new ResponseEntity<>("Precio debe ser mayor a 0", HttpStatus.FORBIDDEN);
        }

        try {
            String urlImagen = fileService.upload(imagen);
            Comida comida = new Comida(nombre,descripcion,tipoComida,precio,urlImagen,disponibilidad);
            comidaRepositorio.save(comida);
            return new ResponseEntity<>("Comida creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al cargar la imagen",HttpStatus.FORBIDDEN);
        }
    }

}