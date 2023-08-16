package com.amadeus.flightsearch.Service.Contrats;


import com.amadeus.flightsearch.DTO.Airport.AirportResponseDto;
import com.amadeus.flightsearch.DTO.Airport.AirportSaveRequestDto;
import com.amadeus.flightsearch.DTO.Airport.AirportUpdateRequestDto;

public interface AirportService extends CRUDService<AirportResponseDto, AirportSaveRequestDto, AirportUpdateRequestDto>{
}
