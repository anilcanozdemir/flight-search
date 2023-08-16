package com.amadeus.flightsearch.Repository;

import com.amadeus.flightsearch.Entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface FlightRepository extends JpaRepository<Flight, Long> {


}