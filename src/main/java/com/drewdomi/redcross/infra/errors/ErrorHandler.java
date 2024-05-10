package com.drewdomi.redcross.infra.errors;

import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = { NoResourceFoundException.class, HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<Object> error404() {
        String errorMessage = "Not found";
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", errorMessage));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> error400(
            MethodArgumentNotValidException ex) {

        String errorMessage = ex.getFieldError().getDefaultMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", errorMessage));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {

        String errorMessage = ex.getMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", errorMessage));
    }

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> error409(
            RuntimeException ex, WebRequest request) {

        String errorMessage = ex.getMessage();
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", errorMessage));
    }
}
