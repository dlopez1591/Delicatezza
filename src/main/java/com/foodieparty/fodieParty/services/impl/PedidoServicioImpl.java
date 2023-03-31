package com.foodieparty.fodieParty.services.impl;

import com.foodieparty.fodieParty.dtos.PedidoDTO;
import com.foodieparty.fodieParty.models.Usuario;
import com.foodieparty.fodieParty.repositories.PedidoRepositorio;
import com.foodieparty.fodieParty.repositories.UsuarioRepositorio;
import com.foodieparty.fodieParty.services.PedidosServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
@Service
public class PedidoServicioImpl implements PedidosServicio {
    @Autowired
    private PedidoRepositorio pedidoRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    @Override
    public List<PedidoDTO> getPedido() {
        return pedidoRepositorio.findAll().stream().map(PedidoDTO::new).collect(toList());
    }

    @Override
    public Optional<PedidoDTO> getPedidoPorId(Long id) {
        return pedidoRepositorio.findById(id).map(PedidoDTO::new);
    }

    @Override
    public List<PedidoDTO> getPedidosUsuario(Authentication authentication) {
        Usuario usuario=usuarioRepositorio.findByEmail(authentication.getName());
        return usuario.getPedidos().stream().map(PedidoDTO::new).collect(toList());
    }
}
