package com.amadeus.flightsearch.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table
@Entity
@Getter
@Setter

public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long airportId;
    @Column
    private String cityName;
    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivingFlightList;
    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departingFlightList;

}
