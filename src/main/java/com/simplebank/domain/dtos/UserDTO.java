package com.simplebank.domain.dtos;

import com.simplebank.domain.enums.UserType;

import java.math.BigDecimal;

public record UserDTO(String fullName,
                      String document,
                      String email,
                      String password,
                      BigDecimal balance,
                      UserType userType) {
}
