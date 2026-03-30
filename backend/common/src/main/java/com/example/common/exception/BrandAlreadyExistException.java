package com.example.common.exception;

public class BrandAlreadyExistException extends RuntimeException {
    public BrandAlreadyExistException(String message) {
        super(message);
    }
}
