package com.example.schedule.exception;

import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException(String message) {
        super(message);
    }
}
