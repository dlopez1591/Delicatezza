package com.foodieparty.fodieParty.repositories;

import com.foodieparty.fodieParty.models.BebidaPedido;
import com.foodieparty.fodieParty.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PedidoRepositorio extends JpaRepository <Pedido, Long>{

}

