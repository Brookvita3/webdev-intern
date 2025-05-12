package com.example.webdev_intern.exception;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends BaseApiException {

    public DataNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }

    public DataNotFoundException(String message, HttpStatus status, Throwable cause) {
        super(message, status, cause);
    }
}
