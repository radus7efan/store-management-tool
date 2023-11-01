package com.demo.smt.controller.advice;

import com.demo.smt.exception.StoreManagementToolException;
import com.demo.smt.model.rest.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.OffsetDateTime;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    private static final String ERROR_MESSAGE = "An ERROR ocurred: ";

    @ExceptionHandler(value = {StoreManagementToolException.class})
    protected ResponseEntity<Error> handleStoreManagementToolException(StoreManagementToolException ex) {
        log.error(ERROR_MESSAGE, ex);
        var error = new Error();
        error.setTitle(ex.getStatus().name());
        error.setDetails(ex.getMessage());
        error.setStatus(ex.getStatus().value());
        error.setTimestamp(OffsetDateTime.now());

        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<Error> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error(ERROR_MESSAGE, ex);
        var error = new Error();
        error.setTitle(HttpStatus.BAD_REQUEST.name());
        error.setDetails(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(OffsetDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AccessDeniedException.class, BadCredentialsException.class})
    protected ResponseEntity<Error> handleAccessDeniedException(RuntimeException ex) {
        log.error(ERROR_MESSAGE, ex);
        var error = new Error();
        error.setTitle(HttpStatus.UNAUTHORIZED.name());
        error.setDetails(ex.getMessage());
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setTimestamp(OffsetDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Error> handleGeneralException(Exception ex) {
        log.error(ERROR_MESSAGE, ex);
        var error = new Error();
        error.setTitle(HttpStatus.INTERNAL_SERVER_ERROR.name());
        error.setDetails("There was an error trying to process your request, please contact support.");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimestamp(OffsetDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
