package com.example.hotelservice.exceptions;

public class ResourceAlreadyExistsException extends Exception{
    public ResourceAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
