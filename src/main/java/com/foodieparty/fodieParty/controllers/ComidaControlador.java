package com.foodieparty.fodieParty.controllers;
import com.foodieparty.fodieParty.dtos.ComidaDTO;
import com.foodieparty.fodieParty.dtos.NuevaComidaDTO;
import com.foodieparty.fodieParty.models.Comida;
import com.foodieparty.fodieParty.repositories.ComidaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ComidaControlador {
    @Autowired
    private ComidaRepositorio comidaRepositorio;

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
            @RequestBody NuevaComidaDTO nuevaComidaDTO
//    NuevaComidaDTO:
//    String nombre, descripcion,imagen;
//    TipoComida tipoComida;
//    Double precio;
//    Boolean disponibilidad;
    ){
        if(nuevaComidaDTO.getNombre()==null){
            return new ResponseEntity<>("Nombre vacio (null)", HttpStatus.FORBIDDEN);
        }
        if(nuevaComidaDTO.getDescripcion()==null){
            return new ResponseEntity<>("Descripcion vacia (null)", HttpStatus.FORBIDDEN);
        }
        if(nuevaComidaDTO.getImagen()==null){
            return new ResponseEntity<>("Imagen vacia (null)", HttpStatus.FORBIDDEN);
        }
        if(nuevaComidaDTO.getTipoComida()==null){
            return new ResponseEntity<>("Tipo vacio (null)", HttpStatus.FORBIDDEN);
        }
        if(nuevaComidaDTO.getDisponibilidad()==null){
            return new ResponseEntity<>("Disponibilidad vacia (null)", HttpStatus.FORBIDDEN);
        }
        if(nuevaComidaDTO.getPrecio()<=0){
            return new ResponseEntity<>("Precio debe ser mayor a 0", HttpStatus.FORBIDDEN);
        }

        Comida comida = new Comida(nuevaComidaDTO);
        comidaRepositorio.save(comida);
        return new ResponseEntity<>("Comida creada exitosamente", HttpStatus.CREATED);
    }

}