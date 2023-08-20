package com.amadeus.flightsearch.DTO.Flight;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//@ApiModel(value = "Flight Save Request DTO",description = "Flight Save Request Data Transfer Object")
public class FlightSaveRequestDto {

    Long arrivalAirportId;

    Long departureAirportId;

    private Date arrivalDate;

    private Date departureDate;

    private Double price;
}
