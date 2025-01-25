package com.simplebank.application.usecases.user;

import com.simplebank.domain.entities.user.User;
import com.simplebank.domain.exceptions.user.UserNotFoundException;
import com.simplebank.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserByDocumentUseCase {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> call(String document) throws UserNotFoundException {

            return this.userRepository.findUserByDocument(document);

    }
}
