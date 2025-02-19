package com.cars24.csms.exceptions;

public class CheckByUsername extends RuntimeException {
    public CheckByUsername(String message) {
        super(message);
    }
}
