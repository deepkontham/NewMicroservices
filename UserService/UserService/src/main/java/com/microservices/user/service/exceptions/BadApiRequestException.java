package com.microservices.user.service.exceptions;

public class BadApiRequestException extends RuntimeException{

    public BadApiRequestException(String message) {
        super(message);
    }

    public BadApiRequestException() {
        super("Bad Request  !!");
    }
}
