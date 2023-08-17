package com.amadeus.flightsearch.Core.Exception;

public class AirportListEmptyException extends EntityListEmptyException{
    public AirportListEmptyException() {
        super("AirportList is empty.");
    }
}
