package com.foodieparty.fodieParty.services;

import com.foodieparty.fodieParty.dtos.BebidaDTO;
import com.foodieparty.fodieParty.models.Bebida;
import com.foodieparty.fodieParty.models.TipoBebida;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BebidaServicio {
    Optional<BebidaDTO> getBebidaPorId( Long id);
    List<BebidaDTO> getBebidas();
    ResponseEntity<Object> crearBebida(
            String nombre,
            String descripcion,
            MultipartFile imagen,
            TipoBebida tipoBebida,
             int disponibilidad,
             Double precio
    );
    void save(Bebida bebida);
}
