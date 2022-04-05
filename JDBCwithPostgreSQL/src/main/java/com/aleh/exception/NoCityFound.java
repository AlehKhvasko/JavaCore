package com.aleh.exception;

public class NoCityFound extends RuntimeException {
    public NoCityFound(String message) {
        super(message);
    }

    public NoCityFound() {
    }
}
