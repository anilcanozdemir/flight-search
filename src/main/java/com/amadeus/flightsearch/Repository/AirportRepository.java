package com.amadeus.flightsearch.Repository;

import com.amadeus.flightsearch.Entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}