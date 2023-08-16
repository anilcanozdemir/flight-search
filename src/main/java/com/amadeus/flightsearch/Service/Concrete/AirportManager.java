package com.amadeus.flightsearch.Service.Concrete;

import com.amadeus.flightsearch.Repository.AirportRepository;
import com.amadeus.flightsearch.Service.Contrats.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportManager implements AirportService {
    private final AirportRepository airportRepository;
}
