package com.htdinh.bookstore.exception;

public class InvalidEnumException extends IllegalArgumentException {
    public InvalidEnumException(String message) {
        super(message);
    }
}