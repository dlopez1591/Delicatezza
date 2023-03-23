package com.foodieparty.fodieParty.repositories;

import com.foodieparty.fodieParty.models.ComidaPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ComidaPedidoRepositorio extends JpaRepository<ComidaPedido, Long> {
}
