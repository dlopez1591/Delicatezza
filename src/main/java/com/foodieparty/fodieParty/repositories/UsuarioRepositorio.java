package com.foodieparty.fodieParty.repositories;

import com.foodieparty.fodieParty.models.BebidaPedido;
import com.foodieparty.fodieParty.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UsuarioRepositorio extends JpaRepository <Usuario, Long>{

}

