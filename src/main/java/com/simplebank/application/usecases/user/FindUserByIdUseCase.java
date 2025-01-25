package com.simplebank.application.usecases.user;

import com.simplebank.domain.entities.user.User;
import com.simplebank.domain.exceptions.user.UserNotFoundException;
import com.simplebank.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class FindUserByIdUseCase {
   @Autowired
   private UserRepository userRepository;

    public User call(UUID id) throws Exception{

        User user = this.userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        return user;
    }
}
