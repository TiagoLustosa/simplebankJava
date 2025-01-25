package com.simplebank.domain.exceptions.user;

public class UserTypeException extends RuntimeException implements IUserException {
    private final String message;

    public UserTypeException(String message) {
        super(message); // Pass the message to RuntimeException
        this.message = message;
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }
}