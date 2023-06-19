package com.sre.transportation.exception;

import org.springframework.http.HttpStatus;

public class ErrorDetail {
    String message;
    HttpStatus status;

    public ErrorDetail(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
