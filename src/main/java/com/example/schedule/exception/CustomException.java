package com.example.schedule.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
