package com.amadeus.flightsearch.Service.Concrete;

import com.amadeus.flightsearch.Core.Exception.AirportListEmptyException;
import com.amadeus.flightsearch.Core.Exception.AirportNotFoundException;
import com.amadeus.flightsearch.Core.Exception.FlightListEmptyException;
import com.amadeus.flightsearch.Core.Exception.FlightNotFoundException;
import com.amadeus.flightsearch.Core.Result.DataResult;
import com.amadeus.flightsearch.Core.Result.Result;
import com.amadeus.flightsearch.Core.Result.SuccessDataResult;
import com.amadeus.flightsearch.Core.Result.SuccessResult;
import com.amadeus.flightsearch.DTO.Airport.AirportResponseDto;
import com.amadeus.flightsearch.DTO.Flight.FlightResponseDto;
import com.amadeus.flightsearch.DTO.Flight.FlightSaveRequestDto;
import com.amadeus.flightsearch.DTO.Flight.FlightUpdateRequestDto;
import com.amadeus.flightsearch.Entity.Airport;
import com.amadeus.flightsearch.Entity.Flight;
import com.amadeus.flightsearch.Repository.FlightRepository;
import com.amadeus.flightsearch.Service.Contrats.FlightService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightManager implements FlightService {
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    @Override
    public Result add(FlightSaveRequestDto flightSaveRequestDto) {
        Flight savedFlight = this.flightRepository.save(modelMapper.map(flightSaveRequestDto, Flight.class));
        return new SuccessResult("Flight created with the id"  + savedFlight.getFlightId());
    }

    @Override
    public DataResult<FlightResponseDto> deleteByid(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        flight.ifPresent(flightRepository::delete);
        return new SuccessDataResult<>("Flight with id  " + id + "  deleted successfully.",
                flight.map(value -> modelMapper.map(value, FlightResponseDto.class))
                        .orElseThrow(() -> new FlightNotFoundException(id)));
    }

    @Override
    public DataResult<List<FlightResponseDto>> getAll() {
        List<Flight> flightList = this.flightRepository.findAll();
        if (flightList.isEmpty()) {
            throw new FlightListEmptyException();
        }
        return new SuccessDataResult<>("FlightList successfully called.",
                flightList.stream()
                        .map(x -> modelMapper.map(x, FlightResponseDto.class))
                        .toList());
    }

    @Override
    public DataResult<FlightResponseDto> getById(Long id) {
        Optional<Flight> flight = this.flightRepository.findById(id);
        return new SuccessDataResult<>("Flight with id " + id + "successfully called.",
                flight.map(value -> modelMapper.map(value, FlightResponseDto.class))
                        .orElseThrow(() -> new FlightNotFoundException(id)));
    }

    @Override
    public Result updateById(FlightUpdateRequestDto flightUpdateRequestDto) {
        Optional<Flight> flightOld = this.flightRepository.findById(flightUpdateRequestDto.getFlightId());
        if (flightOld.isEmpty()) {
            throw new FlightNotFoundException(flightUpdateRequestDto.getFlightId());
        }
        Flight flight = modelMapper.map(flightUpdateRequestDto, Flight.class);
        this.flightRepository.save(flight);
        return new SuccessResult("Successfully updated flight by id" + flight.getFlightId());
    }
}
