package com.challenge.dux.application.exceptions;

public class ErrorRequestException extends RuntimeException {
    public ErrorRequestException(String message) {
        super(message);
    }
}
