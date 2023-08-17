package com.amadeus.flightsearch.ModelMapper;

import com.amadeus.flightsearch.Core.Exception.AirportNotFoundException;
import com.amadeus.flightsearch.DTO.Flight.FlightResponseDto;
import com.amadeus.flightsearch.DTO.Flight.FlightSaveRequestDto;
import com.amadeus.flightsearch.DTO.Flight.FlightUpdateRequestDto;
import com.amadeus.flightsearch.Entity.Flight;
import com.amadeus.flightsearch.Repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightMapper implements MapperProfile<FlightResponseDto, FlightSaveRequestDto, FlightUpdateRequestDto, Flight> {
    private final AirportRepository airportRepository;

    @Override
    public FlightResponseDto entitytoResponseDto(Flight flight) {
        FlightResponseDto flightResponseDto = new FlightResponseDto();
        flightResponseDto.setFlightId(flight.getFlightId());
        flightResponseDto.setPrice(flight.getPrice());
        flightResponseDto.setArrivalDate(flight.getArrivalDate());
        flightResponseDto.setDepartureDate(flight.getDepartureDate());
        flightResponseDto.setArrivalAirportId(flight.getArrivalAirport().getAirportId());
        flightResponseDto.setDepartureAirportId(flight.getDepartureAirport().getAirportId());
        return flightResponseDto;

    }

    @Override
    public Flight saveRequestDtoToEntity(FlightSaveRequestDto flightSaveRequestDto) {
        Flight flight = new Flight();
        flight.setPrice(flightSaveRequestDto.getPrice());
        flight.setArrivalDate(flightSaveRequestDto.getArrivalDate());
        flight.setDepartureDate(flightSaveRequestDto.getDepartureDate());
        flight.setArrivalAirport(airportRepository.findById(flightSaveRequestDto.getArrivalAirportId()).
                orElseThrow(() -> new AirportNotFoundException(flightSaveRequestDto.getArrivalAirportId())));
        flight.setDepartureAirport(airportRepository.findById(flightSaveRequestDto.getDepartureAirportId()).
                orElseThrow(() -> new AirportNotFoundException(flightSaveRequestDto.getDepartureAirportId())));

        return flight;
    }

    @Override
    public Flight updateRequestDtoEntity(FlightUpdateRequestDto flightUpdateRequestDto) {
        Flight flight = new Flight();
        flight.setFlightId(flightUpdateRequestDto.getFlightId());
        flight.setPrice(flightUpdateRequestDto.getPrice());
        flight.setArrivalDate(flightUpdateRequestDto.getArrivalDate());
        flight.setDepartureDate(flightUpdateRequestDto.getDepartureDate());
        flight.setArrivalAirport(airportRepository.findById(flightUpdateRequestDto.getArrivalAirportId()).
                orElseThrow(() -> new AirportNotFoundException(flightUpdateRequestDto.getArrivalAirportId())));
        flight.setDepartureAirport(airportRepository.findById(flightUpdateRequestDto.getDepartureAirportId()).
                orElseThrow(() -> new AirportNotFoundException(flightUpdateRequestDto.getDepartureAirportId())));


        return flight;
    }
}
