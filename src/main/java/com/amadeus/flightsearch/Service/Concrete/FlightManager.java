package com.amadeus.flightsearch.Service.Concrete;

import com.amadeus.flightsearch.Core.Exception.FlightListEmptyException;
import com.amadeus.flightsearch.Core.Exception.FlightNotFoundException;
import com.amadeus.flightsearch.Core.Result.DataResult;
import com.amadeus.flightsearch.Core.Result.Result;
import com.amadeus.flightsearch.Core.Result.SuccessDataResult;
import com.amadeus.flightsearch.Core.Result.SuccessResult;
import com.amadeus.flightsearch.DTO.Flight.FlightResponseDto;
import com.amadeus.flightsearch.DTO.Flight.FlightSaveRequestDto;
import com.amadeus.flightsearch.DTO.Flight.FlightSearchResponseListDto;
import com.amadeus.flightsearch.DTO.Flight.FlightUpdateRequestDto;
import com.amadeus.flightsearch.Entity.Flight;
import com.amadeus.flightsearch.ModelMapper.FlightMapper;
import com.amadeus.flightsearch.Repository.FlightRepository;
import com.amadeus.flightsearch.Service.Contrats.FlightService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

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

    @Override
    public DataResult<FlightSearchResponseListDto> getByPref(Long departureAirportId, Long arrivalAirportId, Date departureDate, Date returnDate) {
        List<Flight> departureFlightList = flightRepository
                .findByDepartureAirport_AirportIdAndArrivalAirport_AirportIdAndDepartureDateBetween(
                        departureAirportId, arrivalAirportId, departureDate, addDay(departureDate));

        List<Flight> returningFlightList = flightRepository.
                findByDepartureAirport_AirportIdAndArrivalAirport_AirportIdAndDepartureDateBetween(
                        arrivalAirportId, departureAirportId, returnDate, addDay(returnDate));

        return new SuccessDataResult<>("FlightSearchResponseLists successfully called."
                , new FlightSearchResponseListDto(returningFlightList.stream().map(flightMapper::entitytoResponseDto).toList()
                , departureFlightList.stream().map(flightMapper::entitytoResponseDto).toList())
        );
    }

    private Date addDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1); //minus number would decrement the days
        return cal.getTime();
    }

    @Override
    public DataResult<List<FlightResponseDto>> getByPref(Long departureAirportId, Long arrivalAirportId, Date departureDate) {
        List<Flight> departureFlightList = flightRepository
                .findByDepartureAirport_AirportIdAndArrivalAirport_AirportIdAndDepartureDateBetween(
                        departureAirportId, arrivalAirportId, departureDate, addDay(departureDate));
        return new SuccessDataResult<>("FlightList successfully called.",
                departureFlightList.stream()
                        .map(flightMapper::entitytoResponseDto)
                        .toList());
    }

    @Override
    @Scheduled(cron = "@daily")
    @Async
    public void getDailyFlightFromExternal() throws Exception {
        System.out.print("called external api call." + new Date());
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();

        // Mock API endpoint ve cevabını tanımlama
        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/api/flight"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"data\" :[\n" +
                                "        {\n" +
                                "            \"arrivalAirportId\": 2,\n" +
                                "            \"departureAirportId\": 1,\n" +
                                "            \"arrivalDate\": \"2016-08-19T00:00:00.000+00:00\",\n" +
                                "            \"departureDate\": \"2016-08-19T00:00:00.000+00:00\",\n" +
                                "            \"price\": 15.0\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"arrivalAirportId\": 1,\n" +
                                "            \"departureAirportId\": 2,\n" +
                                "            \"arrivalDate\": \"2016-08-19T00:00:00.000+00:00\",\n" +
                                "            \"departureDate\": \"2016-08-19T00:00:00.000+00:00\",\n" +
                                "            \"price\": 15.0\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"arrivalAirportId\": 1,\n" +
                                "            \"departureAirportId\": 2,\n" +
                                "            \"arrivalDate\": \"2016-08-19T00:00:00.000+00:00\",\n" +
                                "            \"departureDate\": \"2016-08-19T00:00:00.000+00:00\",\n" +
                                "            \"price\": 15.0\n" +
                                "        }\n" +
                                "    ]}")));

        // Mock API isteği yapma
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://localhost:8080/api/flight"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonResponse = objectMapper.readTree(response.body());
            JsonNode dataArray = jsonResponse.get("data"); // Dizi olan "data" alanını al

            if (dataArray.isArray()) {
                for (JsonNode element : dataArray) {
                    FlightSaveRequestDto object = objectMapper.treeToValue(element, FlightSaveRequestDto.class);
                    this.flightRepository.save(flightMapper.saveRequestDtoToEntity(object));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Mock API Response:\n" + response.body());

        wireMockServer.stop();

    }
}
