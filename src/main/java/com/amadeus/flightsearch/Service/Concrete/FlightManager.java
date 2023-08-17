package com.amadeus.flightsearch.Service.Concrete;

import com.amadeus.flightsearch.Core.Exception.FlightListEmptyException;
import com.amadeus.flightsearch.Core.Exception.FlightNotFoundException;
import com.amadeus.flightsearch.Core.Result.DataResult;
import com.amadeus.flightsearch.Core.Result.Result;
import com.amadeus.flightsearch.Core.Result.SuccessDataResult;
import com.amadeus.flightsearch.Core.Result.SuccessResult;
import com.amadeus.flightsearch.DTO.Flight.FlightResponseDto;
import com.amadeus.flightsearch.DTO.Flight.FlightSaveRequestDto;
import com.amadeus.flightsearch.DTO.Flight.FlightUpdateRequestDto;
import com.amadeus.flightsearch.Entity.Flight;
import com.amadeus.flightsearch.ModelMapper.FlightMapper;
import com.amadeus.flightsearch.Repository.FlightRepository;
import com.amadeus.flightsearch.Service.Contrats.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightManager implements FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Override
    public Result add(FlightSaveRequestDto flightSaveRequestDto) {

        Flight savedFlight = this.flightRepository.save(flightMapper.saveRequestDtoToEntity(flightSaveRequestDto));
        return new SuccessResult("Flight created with the id" + savedFlight.getFlightId());
    }

    @Override
    public DataResult<FlightResponseDto> deleteByid(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        flight.ifPresent(flightRepository::delete);
        return new SuccessDataResult<>("Flight with id  " + id + "  deleted successfully.",
                flight.map(flightMapper::entitytoResponseDto)
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
                        .map(flightMapper::entitytoResponseDto)
                        .toList());
    }

    @Override
    public DataResult<FlightResponseDto> getById(Long id) {
        Optional<Flight> flight = this.flightRepository.findById(id);
        return new SuccessDataResult<>("Flight with id " + id + "successfully called.",
                flight.map(flightMapper::entitytoResponseDto)
                        .orElseThrow(() -> new FlightNotFoundException(id)));
    }

    @Override
    public Result updateById(FlightUpdateRequestDto flightUpdateRequestDto) {
        Optional<Flight> flightOld = this.flightRepository.findById(flightUpdateRequestDto.getFlightId());
        if (flightOld.isEmpty()) {
            throw new FlightNotFoundException(flightUpdateRequestDto.getFlightId());
        }
        Flight flight = flightMapper.updateRequestDtoEntity(flightUpdateRequestDto);
        this.flightRepository.save(flight);
        return new SuccessResult("Successfully updated flight by id" + flight.getFlightId());
    }
}
