package com.example.flights;

import org.springframework.data.repository.CrudRepository;

public interface ReservedFlightRepository extends CrudRepository<ReservedFlight, Long> {
}
