package com.amadeus.flightsearch.DTO.Airport;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@ApiModel(value = "Airport Update Request DTO",description = "Airport Update Request Data Transfer Object")
public class AirportUpdateRequestDto {

    private Long airportId;

    private String cityName;


}
