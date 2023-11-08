package com.amadeus.flightsearch.DTO.Airport;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@ApiModel(value = "Airport Save Request DTO",description = "Airport Save Request Data Transfer Object")
public class AirportSaveRequestDto {

    @NotNull(message = "cityName Null olamaz")
    @NotBlank(message = "cityName Boş Olamaz")
    private String cityName;


}
