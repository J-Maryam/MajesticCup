package org.youcode.majesticcup.common.exceptions.business;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}