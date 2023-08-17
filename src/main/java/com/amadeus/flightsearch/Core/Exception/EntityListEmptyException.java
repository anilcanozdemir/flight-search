package com.amadeus.flightsearch.Core.Exception;

public abstract class EntityListEmptyException extends EntityNotFoundException {

    public EntityListEmptyException(String s) {
        super(s);
    }
}
