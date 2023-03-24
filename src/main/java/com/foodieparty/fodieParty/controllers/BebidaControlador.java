package com.foodieparty.fodieParty.controllers;
import com.foodieparty.fodieParty.dtos.BebidaDTO;
import com.foodieparty.fodieParty.dtos.NuevaBebidaDTO;
import com.foodieparty.fodieParty.models.Bebida;
import com.foodieparty.fodieParty.models.TipoBebida;
import com.foodieparty.fodieParty.repositories.BebidaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BebidaControlador {
    @Autowired
    private BebidaRepositorio bebidaRepositorio;

    @GetMapping("/bebidas")
    public List<BebidaDTO> getBebidas(){
        return bebidaRepositorio.findAll().stream().map(b->new BebidaDTO(b)).collect(Collectors.toList());
    }

    @GetMapping("/bebidas/{id}")
    public Optional<BebidaDTO> getBebidaPorId(@PathVariable Long id){
            return  bebidaRepositorio.findById(id).map(b->new BebidaDTO(b));
    }

    @PostMapping("/crear/bebida")
    public ResponseEntity<Object> crearBebida(
            @RequestBody NuevaBebidaDTO nuevaBebidaDTO
            // NuevaBebidaDTO:
            // String nombre, descripcion, imagen;
            // TipoBebida tipo;
            // int disponibilidad;
            // Double precio;
            ){

        if(nuevaBebidaDTO.getNombre()==null){
            return new ResponseEntity<>("Nombre vacio (null)", HttpStatus.FORBIDDEN);
        }
        if(nuevaBebidaDTO.getDescripcion()==null){
            return new ResponseEntity<>("Descripcion vacia (null)", HttpStatus.FORBIDDEN);
        }
        if(nuevaBebidaDTO.getImagen()==null){
            return new ResponseEntity<>("Imagen vacia (null)", HttpStatus.FORBIDDEN);
        }
        if(nuevaBebidaDTO.getTipoBebida()==null){
            return new ResponseEntity<>("Tipo vacio (null)", HttpStatus.FORBIDDEN);
        }
        if(nuevaBebidaDTO.getDisponibilidad()<=0){
            return new ResponseEntity<>("Disponibilidad debe ser mayor a 0", HttpStatus.FORBIDDEN);
        }
        if(nuevaBebidaDTO.getPrecio()<=0){
            return new ResponseEntity<>("Precio debe ser mayor a 0", HttpStatus.FORBIDDEN);
        }

        Bebida bebida = new Bebida(nuevaBebidaDTO);
        bebidaRepositorio.save(bebida);
        return new ResponseEntity<>("Bebida creada exitosamente", HttpStatus.CREATED);
    }

}
