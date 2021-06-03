package com.example.hotelservice.exceptions;

public class BadResourceException extends Exception{
    public BadResourceException(String errorMessage) {
        super(errorMessage);
    }
}
