package com.amadeus.flightsearch.Repository;

import com.amadeus.flightsearch.Entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {


    List<Flight> findByDepartureAirport_AirportIdAndArrivalAirport_AirportIdAndDepartureDateBetween(
            Long airportId, Long airportId1, Date departureDateStart, Date departureDateEnd);



  

}