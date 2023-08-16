package com.amadeus.flightsearch.DTO.Flight;

import com.amadeus.flightsearch.Entity.Airport;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FlightSaveRequestDto {

    Long arrivalAirportId;

    Long departureAirportId;

    private Date arrivalDate;

    private Date departureDate;

    private Double price;
}
