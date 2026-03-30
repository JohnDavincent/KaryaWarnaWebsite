package com.example.common.exception;

public class BrandNotExistException extends RuntimeException {
    public BrandNotExistException(String message) {
        super(message);
    }
}
