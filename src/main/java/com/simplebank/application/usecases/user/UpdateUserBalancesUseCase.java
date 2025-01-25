package com.simplebank.application.usecases.user;

import com.simplebank.domain.entities.user.User;
import com.simplebank.domain.enums.UserType;
import com.simplebank.domain.exceptions.user.UserInsuficientFundsException;
import com.simplebank.domain.exceptions.user.UserTypeException;
import com.simplebank.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class UpdateUserBalancesUseCase {
   @Autowired
   private UserRepository userRepository;

   public void call(List<User> users, BigDecimal amount) throws Exception {
        User sender = users.get(0);
        User receiver = users.get(1);

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new UserInsuficientFundsException("Saldo insuficiente.");
        }
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new UserTypeException("Lojistas não podem realizar transferência.");
        }
        if (receiver.getUserType() != UserType.MERCHANT) {
            throw new UserTypeException("Somente lojistas podem receber transferência.");
        }

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));

        this.userRepository.save(sender);
        this.userRepository.save(receiver);
     }
}
