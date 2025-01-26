package com.simplebank.services;

import com.simplebank.application.services.AuthorizationServiceImpl;
import com.simplebank.domain.entities.user.User;
import com.simplebank.domain.exceptions.authorization.AuthorizationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorizationServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AuthorizationServiceImpl authorizationServiceImpl;

    public AuthorizationServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should authorize transaction when response is successful and authorization is true")
    void authorizeTransaction_SuccessfulAuthorization() throws Exception {
        // Arrange
        Map<String, Object> mockResponseBody = new HashMap<>();
        mockResponseBody.put("status", "success");
        Map<String, Object> data = new HashMap<>();
        data.put("authorization", true);
        mockResponseBody.put("data", data);

        ResponseEntity<Map> mockResponse = new ResponseEntity<>(mockResponseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(Map.class))).thenReturn(mockResponse);

        // Act
        boolean result = authorizationServiceImpl.authorizeTransaction();

        // Assert
        assertTrue(result);
        verify(restTemplate, times(1)).getForEntity(anyString(), eq(Map.class));
    }

    @Test
    @DisplayName("Should throw AuthorizationException when response status is 'fail'")
    void authorizeTransaction_StatusFail() {
        // Arrange
        Map<String, Object> mockResponseBody = new HashMap<>();
        mockResponseBody.put("status", "fail");
        Map<String, Object> data = new HashMap<>();
        data.put("authorization", false);
        mockResponseBody.put("data", data);

        ResponseEntity<Map> mockResponse = new ResponseEntity<>(mockResponseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(Map.class))).thenReturn(mockResponse);

        // Act & Assert
        AuthorizationException exception = assertThrows(
                AuthorizationException.class,
                () -> authorizationServiceImpl.authorizeTransaction()
        );
        assertEquals("Erro de comunicação a transação falhou", exception.getMessage());
        verify(restTemplate, times(1)).getForEntity(anyString(), eq(Map.class));
    }

    @Test
    @DisplayName("Should throw AuthorizationException when authorization is false")
    void authorizeTransaction_AuthorizationFalse() {
        // Arrange
        Map<String, Object> mockResponseBody = new HashMap<>();
        mockResponseBody.put("status", "success");
        Map<String, Object> data = new HashMap<>();
        data.put("authorization", false);
        mockResponseBody.put("data", data);

        ResponseEntity<Map> mockResponse = new ResponseEntity<>(mockResponseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(Map.class))).thenReturn(mockResponse);

        // Act & Assert
        AuthorizationException exception = assertThrows(
                AuthorizationException.class,
                () -> authorizationServiceImpl.authorizeTransaction()
        );
        assertEquals("Transação não autorizada", exception.getMessage());
        verify(restTemplate, times(1)).getForEntity(anyString(), eq(Map.class));
    }
}
