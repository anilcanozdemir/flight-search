package com.amadeus.flightsearch.DTO.Airport;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@ApiModel(value = "Airport Update Request DTO",description = "Airport Update Request Data Transfer Object")
public class AirportUpdateRequestDto {
    @NotNull(message = "airportId can not be empty")
    private Long airportId;
    @NotNull(message = "cityName Null olamaz")
    @NotBlank(message = "cityName Boş Olamaz")
    private String cityName;


}
