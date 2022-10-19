package com.example.ej147crudvalidation.excepciones;

import com.example.ej147crudvalidation.entity.CustomError;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@ControllerAdvice
@Log
public class Excepciones extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> ExceptionPersona404() {

        return new ResponseEntity<CustomError>(new CustomError(new Date(), HttpStatus.NOT_FOUND.value(), "Entidad Not Found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<CustomError> ExceptionPersona422() {

        return new ResponseEntity<CustomError>(new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Los campos introducidos no cumplen los requisitos"), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
