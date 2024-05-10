package com.drewdomi.redcross.infra.errors;

import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.validation.UnexpectedTypeException;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = {
            NoResourceFoundException.class,
            HttpRequestMethodNotSupportedException.class
    })
    public ResponseEntity<Object> error404() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Not found"));
    }

    @ExceptionHandler(value = {
            DataIntegrityViolationException.class,
            UnexpectedTypeException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<Object> error400(
            RuntimeException ex) {

        final String errorMessage = ex.getMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", errorMessage));
    }

    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            IllegalStateException.class
    })
    protected ResponseEntity<Object> error409(
            RuntimeException ex, WebRequest request) {

        final String errorMessage = ex.getMessage();
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", errorMessage));
    }
}
