package com.example.common.exception;

public class OrderNotExistException extends RuntimeException {
    public OrderNotExistException(String message) {
        super(message);
    }
}
