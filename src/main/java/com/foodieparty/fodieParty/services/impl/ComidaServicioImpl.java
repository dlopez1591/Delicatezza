package com.foodieparty.fodieParty.services.impl;

import com.foodieparty.fodieParty.dtos.ComidaDTO;
import com.foodieparty.fodieParty.models.Comida;
import com.foodieparty.fodieParty.models.ComidaPedido;
import com.foodieparty.fodieParty.models.TipoComida;
import com.foodieparty.fodieParty.repositories.ComidaRepositorio;
import com.foodieparty.fodieParty.services.ComidaServicio;
import com.foodieparty.fodieParty.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComidaServicioImpl implements ComidaServicio {
    @Autowired
    private ComidaRepositorio comidaRepositorio;
    @Autowired
    private FileService fileService;

    @Override
    public List<ComidaDTO> getComidas() {
        return comidaRepositorio.findAll().stream().map(c->new ComidaDTO(c)).collect(Collectors.toList());
    }

    @Override
    public Optional<ComidaDTO> getComidaPorId(Long id) {
        return  comidaRepositorio.findById(id).map(c->new ComidaDTO(c));
    }

    @Override
    public ResponseEntity<Object> crearComida(String nombre, String descripcion, MultipartFile imagen, TipoComida tipoComida, Boolean disponibilidad, Double precio) {
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

    @Override
    public void save(Comida comida) {
        comidaRepositorio.save(comida);
    }
}
