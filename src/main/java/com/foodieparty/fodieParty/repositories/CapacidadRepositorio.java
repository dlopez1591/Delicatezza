package com.foodieparty.fodieParty.repositories;
import com.foodieparty.fodieParty.models.Capacidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CapacidadRepositorio extends JpaRepository<Capacidad, Long> {
}
