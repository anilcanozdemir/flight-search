package com.amadeus.flightsearch.Service.Contrats;

import com.amadeus.flightsearch.Core.Result.DataResult;
import com.amadeus.flightsearch.DTO.Flight.FlightResponseDto;
import com.amadeus.flightsearch.DTO.Flight.FlightSaveRequestDto;
import com.amadeus.flightsearch.DTO.Flight.FlightSearchResponseListDto;
import com.amadeus.flightsearch.DTO.Flight.FlightUpdateRequestDto;

import java.util.Date;
import java.util.List;

public interface FlightService extends CRUDService<FlightResponseDto, FlightSaveRequestDto, FlightUpdateRequestDto> {

    DataResult<FlightSearchResponseListDto> getByPref(Long departureAirportId, Long arrivalAirportId, Date departureDate, Date returnDate);

    DataResult<List<FlightResponseDto>> getByPref(Long departureAirportId, Long arrivalAirportId, Date departureDate);

    void getDailyFlightFromExternal() throws Exception;
}
