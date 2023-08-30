package com.accio.makeMyTrip.Repositories;

import com.accio.makeMyTrip.Entities.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport,Integer> {
}
