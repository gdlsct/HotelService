package com.example.hotelservice.exceptions;

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
