package com.amadeus.flightsearch.DTO.Airport;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@ApiModel(value = "Airport Response DTO",description = "Airport Response Data Transfer Object")
public class AirportResponseDto {
    // @ApiModelProperty(value="Post dto Id alanı.", required = true)
    private Long airportId;
    //  @ApiModelProperty(value="Post dto Id alanı.", required = true)
    private String cityName;


}
