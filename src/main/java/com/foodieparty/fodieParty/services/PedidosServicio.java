package com.foodieparty.fodieParty.services;

import com.foodieparty.fodieParty.dtos.DetallePedidoDTO;
import com.foodieparty.fodieParty.dtos.PedidoDTO;

import com.foodieparty.fodieParty.models.EstadoPedido;
import com.foodieparty.fodieParty.models.Pedido;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;

public interface PedidosServicio {
    List<PedidoDTO> getPedidos();
    Optional<PedidoDTO> getPedidoPorId(Long id);
    List<PedidoDTO> getPedidosUsuario(Authentication authentication);
    void save(Pedido pedido);
    ResponseEntity<Object> crearPedido (
             DetallePedidoDTO detallePedidoDTO,
            Authentication authentication);
    ResponseEntity<Object> editarEstado(
             long id,
             EstadoPedido nuevoEstado
    );
}
