package com.foodieparty.fodieParty.services.impl;

import com.foodieparty.fodieParty.dtos.ReservaDTO;
import com.foodieparty.fodieParty.models.Usuario;

import com.foodieparty.fodieParty.repositories.ReservaRepositorio;
import com.foodieparty.fodieParty.repositories.UsuarioRepositorio;
import com.foodieparty.fodieParty.services.ReservaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ReservaServicioImpl implements ReservaServicio {
    @Autowired
    private ReservaRepositorio reservaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Override
    public List<ReservaDTO> getReserva() {
        return reservaRepositorio.findAll().stream().map(ReservaDTO::new).collect(toList());
    }

    @Override
    public Optional<ReservaDTO> getReservaPorId(Long id) {
        return reservaRepositorio.findById(id).map(ReservaDTO::new);
    }

    @Override
    public List<ReservaDTO> getReservasUsuario(Authentication authentication) {
        Usuario usuario=usuarioRepositorio.findByEmail(authentication.getName());
        return usuario.getReserva().stream().map(ReservaDTO::new).collect(toList());
    }
}
