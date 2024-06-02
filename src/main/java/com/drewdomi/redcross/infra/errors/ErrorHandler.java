package com.drewdomi.redcross.infra.errors;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {
    private static final String MESSAGE = "message";

    @ExceptionHandler(value = {
        NoResourceFoundException.class,
        HttpRequestMethodNotSupportedException.class,
        RescuerNotFoundException.class
    })
    public ResponseEntity<Object> handleNotFoundExceptions(RuntimeException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            response.put(MESSAGE, error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<Object> handleUnexpectedTypeException(UnexpectedTypeException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(value = {
        IllegalArgumentException.class,
        IllegalStateException.class
    })
    public ResponseEntity<Object> handleConflictExceptions(RuntimeException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(Map.of(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException ife) {
            String fieldName = ife.getPath().getFirst().getFieldName();
            String errorMessage = "Invalid " + fieldName;
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(MESSAGE, errorMessage));
        }
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of(MESSAGE, "Invalid request body"));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        final String errorMessage = ex.getMessage();
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(Map.of(MESSAGE, errorMessage));
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class RescuerNotFoundException extends RuntimeException {
        public RescuerNotFoundException(String message) {
            super(message);
        }
    }
}
