package com.drewdomi.redcross.infra.errors;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.validation.UnexpectedTypeException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = {
            NoResourceFoundException.class,
            HttpRequestMethodNotSupportedException.class
    })
    public ResponseEntity<Object> handleNotFoundExceptions() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Not found"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            response.put("message", errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        final String errorMessage = ex.getMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", errorMessage));
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<Object> handleUnexpectedTypeException(UnexpectedTypeException ex) {
        final String errorMessage = ex.getMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", errorMessage));
    }

    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            IllegalStateException.class
    })
    public ResponseEntity<Object> handleConflictExceptions(RuntimeException ex, WebRequest request) {
        final String errorMessage = ex.getMessage();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", errorMessage));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) cause;
            String fieldName = ife.getPath().get(0).getFieldName();
            String errorMessage = "Invalid " + fieldName;
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", errorMessage));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "Invalid request body"));
    }

}
