package com.amadeus.flightsearch.DTO.Flight;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//@ApiModel(value = "Flight Save Request DTO",description = "Flight Save Request Data Transfer Object")
public class FlightSaveRequestDto {
    @NotNull(message = "arrivalAirportId Null olamaz")
    Long arrivalAirportId;
    @NotNull(message = "departureAirportId Null olamaz")
    Long departureAirportId;
    @NotNull(message = "arrivalDate Null olamaz")
    private Date arrivalDate;
    @NotNull(message = "departureDate Null olamaz")
    private Date departureDate;
    @NotNull(message = "price Null olamaz")
    private Double price;
}
