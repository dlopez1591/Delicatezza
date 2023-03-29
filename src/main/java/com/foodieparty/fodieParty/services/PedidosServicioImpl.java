package com.foodieparty.fodieParty.services;

import com.foodieparty.fodieParty.dtos.PedidoDTO;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface PedidosServicioImpl {
    List<PedidoDTO> getPedido();
    Optional<PedidoDTO> getPedidoPorId(Long id);
    List<PedidoDTO> getPedidosUsuario(Authentication authentication);
}
