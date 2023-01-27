package com.Alphanetworks.Animals.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AnimalNotFoundException.class)
    public String HandleDataNotFoundException(Model model, AnimalNotFoundException ex){
        Error error = new Error();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(new Date());
        model.addAttribute("error", error);
        return "error";
    }

    @ExceptionHandler(ForbiddenException.class)
    public String HandleForbiddenException(Model model, ForbiddenException ex){
        Error error = new Error();
        error.setStatusCode(HttpStatus.FORBIDDEN.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(new Date());
        model.addAttribute("error", error);
        return "error";
    }

    @ExceptionHandler(BadRequestException.class)
    public String HandleBadRequestException(Model model, BadRequestException ex){
        Error error = new Error();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(new Date());
        model.addAttribute("error", error);
        return "error";
    }
}
