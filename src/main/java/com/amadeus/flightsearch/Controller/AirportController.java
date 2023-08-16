package com.amadeus.flightsearch.Controller;


import com.amadeus.flightsearch.Core.Result.DataResult;
import com.amadeus.flightsearch.Core.Result.Result;
import com.amadeus.flightsearch.DTO.Airport.AirportResponseDto;
import com.amadeus.flightsearch.DTO.Airport.AirportSaveRequestDto;
import com.amadeus.flightsearch.DTO.Airport.AirportUpdateRequestDto;
import com.amadeus.flightsearch.Service.Contrats.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/airport")
@RestController
@RequiredArgsConstructor
public class AirportController implements CRUDController<AirportResponseDto, AirportSaveRequestDto, AirportUpdateRequestDto> {

    private final AirportService airportService;
    @Override
    public ResponseEntity<Result> add(AirportSaveRequestDto airportSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.airportService.add(airportSaveRequestDto));
    }

    @Override
    public ResponseEntity<DataResult<List<AirportResponseDto>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.airportService.getAll());
    }

    @Override
    public ResponseEntity<DataResult<AirportResponseDto>> getById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.airportService.getById(id));
    }

    @Override
    public ResponseEntity<Result> updateById(AirportUpdateRequestDto airportUpdateRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.airportService.updateById(airportUpdateRequestDto));
    }

    @Override
    public ResponseEntity<DataResult<AirportResponseDto>> deleteById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.airportService.deleteByid(id));
    }
}
