package com.foodieparty.fodieParty.services.impl;

import com.foodieparty.fodieParty.dtos.BebidaDTO;
import com.foodieparty.fodieParty.models.Bebida;
import com.foodieparty.fodieParty.models.TipoBebida;
import com.foodieparty.fodieParty.repositories.BebidaRepositorio;
import com.foodieparty.fodieParty.services.BebidaServicio;
import com.foodieparty.fodieParty.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BebidaServicioImpl implements BebidaServicio {
    @Autowired
    private BebidaRepositorio bebidaRepositorio;
    @Autowired
    private FileService fileService;
    @Override
    public List<BebidaDTO> getBebidas(){
        return bebidaRepositorio.findAll().stream().map(b->new BebidaDTO(b)).collect(Collectors.toList());
    }
    @Override
    public Optional<BebidaDTO> getBebidaPorId(Long id){
        return  bebidaRepositorio.findById(id).map(b->new BebidaDTO(b));
    }
    @Override
    public ResponseEntity<Object> crearBebida(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("tipoBebida") TipoBebida tipoBebida,
            @RequestParam("disponibilidad") int disponibilidad,
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
        if(tipoBebida==null){
            return new ResponseEntity<>("Tipo vacio (null)", HttpStatus.FORBIDDEN);
        }
        if(disponibilidad<=0){
            return new ResponseEntity<>("Disponibilidad debe ser mayor a 0", HttpStatus.FORBIDDEN);
        }
        if(precio<=0){
            return new ResponseEntity<>("Precio debe ser mayor a 0", HttpStatus.FORBIDDEN);
        }
        try {
            String urlImagen = fileService.upload(imagen);
            Bebida bebida = new Bebida(nombre,descripcion,urlImagen,tipoBebida,disponibilidad,precio);
            bebidaRepositorio.save(bebida);
            return new ResponseEntity<>("Bebida creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al cargar la imagen",HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public void save(Bebida bebida) {
        bebidaRepositorio.save(bebida);
    }

    @Override
    public Boolean stockListaBebidas(long id, long cant) {
        return bebidaRepositorio.findById(id).get().tieneStock(cant);
    }
}
