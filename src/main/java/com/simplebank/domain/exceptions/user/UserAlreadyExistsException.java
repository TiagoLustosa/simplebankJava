package com.simplebank.domain.exceptions.user;

public class UserAlreadyExistsException extends Exception implements IUserException{private final String message;
    public UserAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }
}
