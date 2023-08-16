package com.amadeus.flightsearch.Service.Contrats;

import com.amadeus.flightsearch.DTO.Flight.FlightResponseDto;
import com.amadeus.flightsearch.DTO.Flight.FlightSaveRequestDto;
import com.amadeus.flightsearch.DTO.Flight.FlightUpdateRequestDto;

public interface FlightService extends CRUDService<FlightResponseDto, FlightSaveRequestDto, FlightUpdateRequestDto> {

}
