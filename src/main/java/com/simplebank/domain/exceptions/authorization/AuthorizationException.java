package com.simplebank.domain.exceptions.authorization;

import com.simplebank.domain.exceptions.user.IUserException;

public class AuthorizationException extends RuntimeException implements IAuthorizationException {
    private final String message;

    public AuthorizationException(String message) {
        super(message); // Pass the message to RuntimeException
        this.message = message;
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }
}