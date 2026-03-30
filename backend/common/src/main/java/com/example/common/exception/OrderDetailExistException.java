package com.example.common.exception;

public class OrderDetailExistException extends RuntimeException {
    public OrderDetailExistException(String message) {
        super(message);
    }
}
