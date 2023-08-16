package com.amadeus.flightsearch.DTO.Flight;

import com.amadeus.flightsearch.Entity.Airport;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FlightResponseDto {

    Long arrivalAirportId;

    Long departureAirportId;

    private Long flightId;

    private Date arrivalDate;

    private Date departureDate;

    private Double price;
}
