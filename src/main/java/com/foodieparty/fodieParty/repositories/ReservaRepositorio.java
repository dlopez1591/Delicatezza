package com.foodieparty.fodieParty.repositories;

import com.foodieparty.fodieParty.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {
}
