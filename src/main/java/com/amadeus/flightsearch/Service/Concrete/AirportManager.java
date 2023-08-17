package com.amadeus.flightsearch.Service.Concrete;

import com.amadeus.flightsearch.Core.Exception.AirportListEmptyException;
import com.amadeus.flightsearch.Core.Exception.AirportNotFoundException;
import com.amadeus.flightsearch.Core.Result.DataResult;
import com.amadeus.flightsearch.Core.Result.Result;
import com.amadeus.flightsearch.Core.Result.SuccessDataResult;
import com.amadeus.flightsearch.Core.Result.SuccessResult;
import com.amadeus.flightsearch.DTO.Airport.AirportResponseDto;
import com.amadeus.flightsearch.DTO.Airport.AirportSaveRequestDto;
import com.amadeus.flightsearch.DTO.Airport.AirportUpdateRequestDto;
import com.amadeus.flightsearch.Entity.Airport;
import com.amadeus.flightsearch.ModelMapper.AirportMapper;
import com.amadeus.flightsearch.Repository.AirportRepository;
import com.amadeus.flightsearch.Service.Contrats.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportManager implements AirportService {
    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Override
    public Result add(AirportSaveRequestDto airportSaveRequestDto) {
        Airport savedAirport = this.airportRepository.save(airportMapper.saveRequestDtoToEntity(airportSaveRequestDto));
        return new SuccessResult("To city   :" + savedAirport.getCityName() +
                " with id  : " + savedAirport.getAirportId() +
                "  airport added.");
    }

    @Override
    public DataResult<AirportResponseDto> deleteByid(Long id) {
        Optional<Airport> airport = airportRepository.findById(id);
        airport.ifPresent(airportRepository::delete);
        return new SuccessDataResult<>("Company with id  " + id + "  deleted successfully.",
                airport.map(airportMapper::entitytoResponseDto)
                        .orElseThrow(() -> new AirportNotFoundException(id)));


    }

    @Override
    public DataResult<List<AirportResponseDto>> getAll() {
        List<Airport> airportList = this.airportRepository.findAll();
        if (airportList.isEmpty()) {
            throw new AirportListEmptyException();
        }
        return new SuccessDataResult<>("CompanyList successfully called.",
                airportList.stream()
                        .map(airportMapper::entitytoResponseDto)
                        .toList());

    }

    @Override
    public DataResult<AirportResponseDto> getById(Long id) {
        Optional<Airport> airport = this.airportRepository.findById(id);
        return new SuccessDataResult<>("Company with id " + id + "successfully called.",
                airport.map(airportMapper::entitytoResponseDto)
                        .orElseThrow(() -> new AirportNotFoundException(id)));

    }

    @Override
    public Result updateById(AirportUpdateRequestDto airportUpdateRequestDto) {
        Optional<Airport> airportOld = this.airportRepository.findById(airportUpdateRequestDto.getAirportId());
        if (airportOld.isPresent()) {
            if (!airportOld.get().getCityName().equals(airportUpdateRequestDto.getCityName())) {
                this.airportRepository.save(airportMapper.updateRequestDtoEntity(airportUpdateRequestDto));
            }
            return new SuccessResult("To city   :" + airportUpdateRequestDto.getCityName() +
                    "with id  :" + airportUpdateRequestDto.getAirportId() +
                    " airport updated.");
        }

        throw new AirportNotFoundException(airportUpdateRequestDto.getAirportId());

    }
}
