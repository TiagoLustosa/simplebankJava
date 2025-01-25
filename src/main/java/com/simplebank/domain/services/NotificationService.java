package com.simplebank.domain.services;
import com.simplebank.domain.entities.user.User;

public interface NotificationService {
    public void sendNotification(User user, String message) throws Exception;
}
