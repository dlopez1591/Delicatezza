package com.foodieparty.fodieParty.repositories;

import com.foodieparty.fodieParty.models.BebidaPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BebidaPedidoRepositorio extends JpaRepository <BebidaPedido, Long>{

}

