package com.fra.myapi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FoodNotFoundException extends RuntimeException {

    public FoodNotFoundException(Long id) {
        super(String.format("Not found food with ID: %d", id));
    }
}
