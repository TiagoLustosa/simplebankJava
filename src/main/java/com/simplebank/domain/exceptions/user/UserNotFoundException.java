package com.simplebank.domain.exceptions.user;

import com.simplebank.domain.exceptions.user.IUserException;

public class UserNotFoundException extends RuntimeException implements IUserException {
    private final String message;

    public UserNotFoundException(String message) {
        super(message); // Pass the message to RuntimeException
        this.message = message;
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }
}