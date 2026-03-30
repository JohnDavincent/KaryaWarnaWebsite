package com.example.common.exception;

public class PrefixAlreadyExistException extends RuntimeException {
    public PrefixAlreadyExistException(String message) {
        super(message);
    }
}
