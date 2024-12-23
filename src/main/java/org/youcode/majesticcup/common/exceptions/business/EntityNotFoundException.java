package org.youcode.majesticcup.common.exceptions.business;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
