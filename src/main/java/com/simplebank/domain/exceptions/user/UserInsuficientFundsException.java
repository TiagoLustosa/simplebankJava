package com.simplebank.domain.exceptions.user;

public class UserInsuficientFundsException extends RuntimeException implements IUserException {
    private final String message;

    public UserInsuficientFundsException(String message) {
        super(message); // Pass the message to RuntimeException
        this.message = message;
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }
}