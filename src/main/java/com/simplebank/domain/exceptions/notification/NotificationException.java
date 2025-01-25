package com.simplebank.domain.exceptions.notification;

import com.simplebank.domain.exceptions.authorization.IAuthorizationException;

public class NotificationException extends Exception {
    private final String message;

    public NotificationException(String message) {
        super(message); // Pass the message to RuntimeException
        this.message = message;
    }

}