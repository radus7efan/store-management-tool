package com.demo.smt.exception;

import org.springframework.http.HttpStatus;

public class StoreManagementToolException extends RuntimeException {

    private final HttpStatus status;

    public StoreManagementToolException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public StoreManagementToolException(HttpStatus status, String message, Object... arguments) {
        super(String.format(message, arguments));
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}
