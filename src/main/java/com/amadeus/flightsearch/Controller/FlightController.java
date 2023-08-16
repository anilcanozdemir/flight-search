package com.amadeus.flightsearch.Controller;

import com.amadeus.flightsearch.Core.Result.DataResult;
import com.amadeus.flightsearch.Core.Result.Result;
import com.amadeus.flightsearch.DTO.Flight.FlightResponseDto;
import com.amadeus.flightsearch.DTO.Flight.FlightSaveRequestDto;
import com.amadeus.flightsearch.DTO.Flight.FlightUpdateRequestDto;
import com.amadeus.flightsearch.Service.Contrats.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.core.support.FragmentNotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/flight")
@RestController
@RequiredArgsConstructor
public class FlightController implements  CRUDController<FlightResponseDto, FlightSaveRequestDto, FlightUpdateRequestDto>{
    private final FlightService flightService;
    @Override
    @PostMapping("/add")
    public ResponseEntity<Result> add(FlightSaveRequestDto flightSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.flightService.add(flightSaveRequestDto));
    }

    @Override
    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<FlightResponseDto>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.flightService.getAll());
    }

    @Override
    @GetMapping("/getById")
    public ResponseEntity<DataResult<FlightResponseDto>> getById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.flightService.getById(id));
    }

    @Override
    @PostMapping("/updateById")
    public ResponseEntity<Result> updateById(FlightUpdateRequestDto flightUpdateRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.flightService.updateById(flightUpdateRequestDto));
    }

    @Override
    @PostMapping("/deleteById")
    public ResponseEntity<DataResult<FlightResponseDto>> deleteById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.flightService.deleteByid(id));
    }
}
