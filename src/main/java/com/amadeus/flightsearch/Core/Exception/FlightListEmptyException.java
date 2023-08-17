package com.amadeus.flightsearch.Core.Exception;

public class FlightListEmptyException extends EntityListEmptyException {
    public FlightListEmptyException() {
        super("FlightList is empty.");
    }
}
