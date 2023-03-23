package com.foodieparty.fodieParty.repositories;

import com.foodieparty.fodieParty.models.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ComidaRepositorio extends JpaRepository<Comida, Long> {
}
