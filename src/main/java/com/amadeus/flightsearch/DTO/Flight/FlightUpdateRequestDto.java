package com.amadeus.flightsearch.DTO.Flight;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//@ApiModel(value = "Flight Update Request DTO",description = "Flight Update Request Data Transfer Object")

public class FlightUpdateRequestDto {

    Long arrivalAirportId;

    Long departureAirportId;

    private Long flightId;

    private Date arrivalDate;

    private Date departureDate;

    private Double price;
}
