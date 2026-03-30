package com.example.common.exception;

public class ProductNotExistException extends RuntimeException {
    public ProductNotExistException(String message) {
        super(message);
    }
}
