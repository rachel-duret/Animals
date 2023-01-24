package com.Alphanetworks.Animals.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

public class GlobalExceptionHandler {
    @ExceptionHandler(AnimalNotFoundException.class)
    public ResponseEntity<Error> HandleDataNotFoundException(AnimalNotFoundException ex){
        Error error = new Error();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(new Date());
        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Error> HandleForbiddenException(ForbiddenException ex){
        Error error = new Error();
        error.setStatusCode(HttpStatus.FORBIDDEN.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(new Date());
        return new ResponseEntity<Error>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Error> HandleBadRequestException(BadRequestException ex){
        Error error = new Error();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(new Date());
        return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
    }
}
