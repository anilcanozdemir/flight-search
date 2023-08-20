package com.amadeus.flightsearch.DTO.Flight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
//@ApiModel(value = "Flight Search Response DTO",description = "Flight Search Response Data Transfer Object")
public class FlightSearchResponseListDto {
    private List<FlightResponseDto> returningFlightDtoList;
    private List<FlightResponseDto> departureFlightDtoList;
}
