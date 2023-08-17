package com.amadeus.flightsearch.ModelMapper;

import com.amadeus.flightsearch.DTO.Airport.AirportResponseDto;
import com.amadeus.flightsearch.DTO.Airport.AirportSaveRequestDto;
import com.amadeus.flightsearch.DTO.Airport.AirportUpdateRequestDto;
import com.amadeus.flightsearch.Entity.Airport;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper implements MapperProfile<AirportResponseDto, AirportSaveRequestDto, AirportUpdateRequestDto, Airport> {
    @Override
    public AirportResponseDto entitytoResponseDto(Airport airport) {
        AirportResponseDto airportResponseDto=new AirportResponseDto();
        airportResponseDto.setAirportId(airport.getAirportId());
        airportResponseDto.setCityName(airport.getCityName());


        return airportResponseDto;
    }

    @Override
    public Airport saveRequestDtoToEntity(AirportSaveRequestDto airportSaveRequestDto) {
       Airport airport=new Airport();
       airport.setCityName(airportSaveRequestDto.getCityName());
       return airport;
    }

    @Override
    public Airport updateRequestDtoEntity(AirportUpdateRequestDto airportUpdateRequestDto) {
        Airport airport=new Airport();
        airport.setCityName(airportUpdateRequestDto.getCityName());
        airport.setAirportId(airportUpdateRequestDto.getAirportId());
        return airport;
    }
}
