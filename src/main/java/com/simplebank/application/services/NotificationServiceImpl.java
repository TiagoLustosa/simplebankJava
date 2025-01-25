package com.simplebank.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplebank.domain.dtos.NotificationDTO;
import com.simplebank.domain.entities.user.User;
import com.simplebank.domain.exceptions.notification.NotificationException;
import com.simplebank.domain.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final String NOTIFICATION_URL = "https://util.devi.tools/api/v1/notify";
    private static final String STATUS_SUCCESS = "success";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper; // Direct injection of ObjectMapper for better reusability

    public void sendNotification(User user, String message) throws NotificationException {
        try {
            // Create request payload
            NotificationDTO notificationRequest = new NotificationDTO(user.getEmail(), message);

            // Send notification
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Criar http entity
            HttpEntity<NotificationDTO> requestEntity = new HttpEntity<>(notificationRequest, headers);

            // Enviar requisição e capturar resposta completa
            ResponseEntity<String> response = restTemplate.exchange(
                    NOTIFICATION_URL,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );


        } catch (Exception e) {
            throw new NotificationException("Failed to send notification");
        }
    }
  }

