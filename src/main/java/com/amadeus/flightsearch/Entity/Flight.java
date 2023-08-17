package com.amadeus.flightsearch.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table
@Entity
@Getter
@Setter

public class Flight {
    @JoinColumn(name = "arrivalAirportId")
    @ManyToOne
    Airport arrivalAirport;
    @JoinColumn(name = "departureAirportId")
    @ManyToOne
    Airport departureAirport;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long flightId;
    @Column
    private Date arrivalDate;
    @Column
    private Date departureDate;
    @Column
    private Double price;
}
