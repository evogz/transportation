package com.sre.transportation.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        Map<String, String> details = new HashMap<>();
        errorMap.put("status", HttpStatus.BAD_REQUEST.toString());
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            details.put(error.getField(), error.getDefaultMessage());
        });
        errorMap.put("details", details.values().toString());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String, String> handleNotFoundArgument(NoSuchElementException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("status", HttpStatus.BAD_REQUEST.toString());
        errorMap.put("details", "Record does not exist");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public Map<String, String> handleEmptyData(EmptyResultDataAccessException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("status", HttpStatus.NOT_FOUND.toString());
        errorMap.put("details", "Record does not exist");
        return errorMap;
    }
}