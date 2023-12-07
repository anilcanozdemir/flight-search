package com.amadeus.flightsearch.ExceptionHandling;

import com.amadeus.flightsearch.Core.Exception.EntityListEmptyException;
import com.amadeus.flightsearch.Core.Exception.EntityNotFoundException;
import com.amadeus.flightsearch.Core.Result.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerConfig {
    @ExceptionHandler({EntityListEmptyException.class})
    public ResponseEntity<ErrorResult> handleEntityListEmptyException(EntityListEmptyException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(exception.getMessage()));
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorResult> handleEntityListEmptyException(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(exception.getMessage()));
    }
}
