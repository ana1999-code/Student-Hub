package com.example.studenthub.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ApiException {

    private final String message;

    private final HttpStatus httpStatus;

    private final LocalDateTime timestamp;
}
