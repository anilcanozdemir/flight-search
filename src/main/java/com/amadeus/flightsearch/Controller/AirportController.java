package com.amadeus.flightsearch.Controller;


import com.amadeus.flightsearch.Core.Result.DataResult;
import com.amadeus.flightsearch.Core.Result.Result;
import com.amadeus.flightsearch.DTO.Airport.AirportResponseDto;
import com.amadeus.flightsearch.DTO.Airport.AirportSaveRequestDto;
import com.amadeus.flightsearch.DTO.Airport.AirportUpdateRequestDto;
import com.amadeus.flightsearch.Service.Contrats.AirportService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/airport")
@RestController
@RequiredArgsConstructor

@ApiResponse(responseCode = "500", description = "Internal server error, this should not happen")
@Tag(name = "Airport API Dökümasyonu", description = "Airport CRUD işlemleri için endpoint")
public class AirportController implements CRUDController<AirportResponseDto, AirportSaveRequestDto, AirportUpdateRequestDto> {

    private final AirportService airportService;

    @Override
    @PostMapping("/add")
    @ApiResponse(responseCode = "401", description = "Unauthorized, login required")
    //  @ApiOperation(value="Airport ekler")

    public ResponseEntity<Result> add(@RequestBody /*@ApiParam(value = "Airport Save Request Data",required = true) */AirportSaveRequestDto airportSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.airportService.add(airportSaveRequestDto));
    }

    @Override
    @GetMapping("/getAll")
    //     @ApiOperation(value="Airport listesi döner")
    public ResponseEntity<DataResult<List<AirportResponseDto>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.airportService.getAll());
    }

    @Override
    @GetMapping("/getById")
    //  @ApiOperation(value="Id ye göre Airport döner")
    public ResponseEntity<DataResult<AirportResponseDto>> getById(@RequestParam/* @ApiParam(value = "Airport id",required = true)*/  Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.airportService.getById(id));
    }

    @Override
    @ApiResponse(responseCode = "401", description = "Unauthorized, login required")
    @PostMapping("/updateById")
    // @ApiOperation(value="Id ye göre Airport güncellemesi yapar.")
    public ResponseEntity<Result> updateById(@RequestBody/* @ApiParam(value = "AirportUpdateRequestDto",required = true)*/ AirportUpdateRequestDto airportUpdateRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.airportService.updateById(airportUpdateRequestDto));
    }

    @Override

    @PostMapping("/deleteById")
    @ApiResponse(responseCode = "401", description = "Unauthorized, login required")
    //@ApiOperation(value="Id ye göre Airport silmesi yapar.")
    public ResponseEntity<DataResult<AirportResponseDto>> deleteById(@RequestParam /*@ApiParam(value = "Airport id",required = true) */Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.airportService.deleteById(id));
    }
}
