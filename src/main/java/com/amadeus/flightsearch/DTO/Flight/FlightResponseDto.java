package com.amadeus.flightsearch.DTO.Flight;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//@ApiModel(value = "Flight Response DTO",description = "Flight Response Data Transfer Object")
public class FlightResponseDto {

    Long arrivalAirportId;

    Long departureAirportId;

    private Long flightId;

    private Date arrivalDate;

    private Date departureDate;

    private Double price;
}
