package com.project.errors;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;
import java.time.LocalDateTime;


@Getter
@Setter
public class AppException extends RuntimeException {

    private HttpStatus status;
    private String message;
    private Object[] params;
    private LocalDateTime timestamp;

    public AppException(HttpStatus status) {
        this.status = status;
        timestamp = LocalDateTime.now();
    }

    public AppException(HttpStatus status, String message) {
        this(status);
        this.message = message;
    }

    public AppException(HttpStatus status, String message, Object... params) {
        this(status);
        this.message = MessageFormat.format(message, params);
    }
}
