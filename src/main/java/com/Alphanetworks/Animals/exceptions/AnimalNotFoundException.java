package com.Alphanetworks.Animals.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnimalNotFoundException extends RuntimeException{
    private static final long serialVerisionUID = 1;

    public AnimalNotFoundException(String message){
        super(message);
    }
}
