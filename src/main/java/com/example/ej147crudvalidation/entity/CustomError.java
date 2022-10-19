package com.example.ej147crudvalidation.entity;

import lombok.Data;

import java.util.Date;
@Data
public class CustomError {

    private final Date timestamp;
    private final int httpCode;
    private final String message;

    public CustomError(Date timestamp, int httpCode, String message) {
        this.timestamp = timestamp;
        this.httpCode = httpCode;
        this.message = message;
    }
}
