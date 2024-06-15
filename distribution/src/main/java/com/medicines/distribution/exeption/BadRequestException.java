package com.medicines.distribution.exeption;

public class BadRequestException extends  RuntimeException{


    public BadRequestException(String message) {
        super(message);
    }
}
