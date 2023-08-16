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
    @JoinColumn
    @ManyToOne
    Airport arrivalAirport;
    @JoinColumn
    @ManyToOne
    Airport departureAirport;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Date arrivalDate;
    @Column
    private Date departureDate;

}