package com.accio.makeMyTrip.Repositories;

import com.accio.makeMyTrip.Entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Integer> {
}
