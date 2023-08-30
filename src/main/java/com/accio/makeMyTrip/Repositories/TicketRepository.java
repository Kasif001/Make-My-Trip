package com.accio.makeMyTrip.Repositories;

import com.accio.makeMyTrip.Entities.Ticket;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
