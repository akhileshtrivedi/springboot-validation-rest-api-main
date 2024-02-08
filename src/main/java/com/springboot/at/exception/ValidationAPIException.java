package com.springboot.at.exception;

import org.springframework.http.HttpStatus;

public class ValidationAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public ValidationAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ValidationAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
