package org.youcode.majesticcup.common.exceptions.business;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super(username);
    }
}
