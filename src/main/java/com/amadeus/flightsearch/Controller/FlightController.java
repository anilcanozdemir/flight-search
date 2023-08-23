package com.amadeus.flightsearch.Controller;

import com.amadeus.flightsearch.Core.Result.DataResult;
import com.amadeus.flightsearch.Core.Result.Result;
import com.amadeus.flightsearch.DTO.Flight.FlightResponseDto;
import com.amadeus.flightsearch.DTO.Flight.FlightSaveRequestDto;
import com.amadeus.flightsearch.DTO.Flight.FlightUpdateRequestDto;
import com.amadeus.flightsearch.Service.Contrats.FlightService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RequestMapping("/flight")
@RestController
@RequiredArgsConstructor
//@Api(value = "Flight API Dökümasyonu")
@ApiResponse(responseCode = "401", description = "Unauthorized, login required")
@ApiResponse(responseCode = "500", description = "Internal server error, this should not happen")
@Tag(name = "Flight API Dökümasyonu", description = "Flight CRUD işlemleri için endpoint")
public class FlightController implements CRUDController<FlightResponseDto, FlightSaveRequestDto, FlightUpdateRequestDto> {
    private final FlightService flightService;

    @Override
    @PostMapping("/add")
    // @ApiOperation(value = "Flight ekler")
    public ResponseEntity<Result> add(@RequestBody /*@ApiParam(value = "FlightSaveRequestDto", required = true)*/ FlightSaveRequestDto flightSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.flightService.add(flightSaveRequestDto));
    }

    @Override
    @GetMapping("/getAll")
    // @ApiOperation(value = "Airport listesi döner")
    public ResponseEntity<DataResult<List<FlightResponseDto>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.flightService.getAll());
    }

    @Override
    @GetMapping("/getById")
    // @ApiOperation(value = "Id ye göre Airport döner")
    public ResponseEntity<DataResult<FlightResponseDto>> getById(@RequestParam /*@ApiParam(value = "Flight id", required = true)*/ Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.flightService.getById(id));
    }

    @Override
    @PostMapping("/updateById")
    //  @ApiOperation(value = "Id ye göre Airport güncellemesi yapar.")
    public ResponseEntity<Result> updateById(@RequestBody/* @ApiParam(value = "FlightUpdateRequestDto", required = true) */FlightUpdateRequestDto flightUpdateRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.flightService.updateById(flightUpdateRequestDto));
    }

    @Override
    @PostMapping("/deleteById")
    //  @ApiOperation(value = "Id ye göre Airport silmesi yapar.")
    public ResponseEntity<DataResult<FlightResponseDto>> deleteById(@RequestParam /*@ApiParam(value = "Flight id", required = true)*/ Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.flightService.deleteByid(id));
    }

    @GetMapping("/getByPref")
    //  @ApiOperation(value = "İsteklere göre Uçuş listeleri döner.")
    public ResponseEntity<DataResult> getByPref(
            @RequestParam /*@ApiParam(value = "Departure Airport id", required = true)*/ Long departureAirportId
            , @RequestParam /*@ApiParam(value = "Arrival Airport id", required = true)*/ Long arrivalAirportId
            , @RequestParam/* @ApiParam(value = "Departure date", required = true)*/ @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate
            , @RequestParam(required = false)/* @ApiParam(value = "Optional return date", required = false)*/ @DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDate) {
        if (returnDate == null) {
            return ResponseEntity.status(HttpStatus.OK).body(this.flightService.getByPref(departureAirportId, arrivalAirportId, departureDate));
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.flightService.getByPref(departureAirportId, arrivalAirportId, departureDate, returnDate));
    }
}
