package com.example.flights;

import com.example.flights.security.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findByOwner(User owner);

}
