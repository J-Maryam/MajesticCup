package org.youcode.majesticcup.common.exceptions.business;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
