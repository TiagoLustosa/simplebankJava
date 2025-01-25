package com.simplebank.domain.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDTO(BigDecimal amount, String senderDocument, String receiverDocument){

}

