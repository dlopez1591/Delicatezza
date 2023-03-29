package com.foodieparty.fodieParty.services;

import com.foodieparty.fodieParty.dtos.ComidaDTO;
import com.foodieparty.fodieParty.models.TipoComida;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ComidaServicio {
    List<ComidaDTO> getComidas();
    Optional<ComidaDTO> getComidaPorId(Long id);
    ResponseEntity<Object> crearComida(
           String nombre,
            String descripcion,
            MultipartFile imagen,
            TipoComida tipoComida,
            Boolean disponibilidad,
            Double precio
    );
}
