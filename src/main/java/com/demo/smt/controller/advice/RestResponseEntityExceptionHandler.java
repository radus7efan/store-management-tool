package com.demo.smt.controller.advice;

import com.demo.smt.exception.StoreManagementToolException;
import com.demo.smt.model.rest.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = {StoreManagementToolException.class})
    protected ResponseEntity<Error> handleStoreManagementToolException(StoreManagementToolException ex) {
        var error = new Error();
        error.setTitle(ex.getStatus().name());
        error.setDetails(ex.getMessage());
        error.setStatus(ex.getStatus().value());
        error.setTimestamp(OffsetDateTime.now());

        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<Error> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        var error = new Error();
        error.setTitle(HttpStatus.BAD_REQUEST.name());
        error.setDetails(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(OffsetDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Error> handleGeneralException(Exception ex) {
        var error = new Error();
        error.setTitle(HttpStatus.INTERNAL_SERVER_ERROR.name());
        error.setDetails("There was an error trying to process your request, please contact support.");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimestamp(OffsetDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
