package com.example.flights;

import org.springframework.data.repository.CrudRepository;

public interface AirportRepository extends CrudRepository<Airport, String> {
}
