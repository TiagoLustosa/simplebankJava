package com.simplebank.application.services;

import com.simplebank.domain.entities.user.User;
import com.simplebank.domain.exceptions.authorization.AuthorizationException;
import com.simplebank.domain.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    public boolean authorizeTransaction() {
        ResponseEntity<Map> authorizeResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        Map<String, Object> body = authorizeResponse.getBody();
        String statusMessage = (String) body.get("status");
        Map<String, Object> data = (Map<String, Object>) body.get("data");

        Boolean authorization = (Boolean) data.get("authorization");
        if(statusMessage.equalsIgnoreCase("fail")){
            throw new AuthorizationException("Erro de comunicação a transação falhou");
        }
        if(!authorization) {
            throw new AuthorizationException("Transação não autorizada");
        }
        return authorization;
    }
}