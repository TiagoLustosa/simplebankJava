package com.simplebank.domain.services;
import com.simplebank.domain.entities.user.User;
import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    public List<User> getTransactionUsers(String senderDocument, String receiverDocument) throws Exception;
    public void updateUserBalances(List<User> users, BigDecimal amount) throws Exception;
}
