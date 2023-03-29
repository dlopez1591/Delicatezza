package com.foodieparty.fodieParty.services;

import com.foodieparty.fodieParty.dtos.PedidoDTO;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import java.util.List;
import java.util.Optional;

public interface PedidosServicio {
    List<PedidoDTO> getPedido();
    Optional<PedidoDTO> getPedidoPorId(Long id);
    List<PedidoDTO> getPedidosUsuario(Authentication authentication);
}