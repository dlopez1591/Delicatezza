package com.foodieparty.fodieParty.repositories;

import com.foodieparty.fodieParty.models.TicketReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketReservaRepositorio extends JpaRepository<TicketReserva, Long> {
}
