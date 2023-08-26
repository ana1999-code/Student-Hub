package com.example.studenthub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(
            ApiRequestException apiRequestException
    ){
        final ApiException apiException = new ApiException(
                apiRequestException.getMessage(),
                BAD_REQUEST,
                LocalDateTime.now());

        return new ResponseEntity<>(apiException, BAD_REQUEST);
    }
}
