package com.foodieparty.fodieParty.repositories;

import com.foodieparty.fodieParty.models.TicketPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketPedidoRepositorio extends JpaRepository<TicketPedido,Long> {
}
