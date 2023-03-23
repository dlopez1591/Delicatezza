package com.foodieparty.fodieParty.repositories;
import com.foodieparty.fodieParty.models.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MesaRepositorio extends JpaRepository <Mesa, Long>{

}

