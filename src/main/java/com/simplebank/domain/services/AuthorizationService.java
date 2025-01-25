package com.simplebank.domain.services;

import com.simplebank.domain.entities.user.User;

import java.math.BigDecimal;

public interface AuthorizationService {
    public boolean authorizeTransaction() throws Exception;
}
