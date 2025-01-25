package com.simplebank.application.usecases.user;

import com.simplebank.domain.entities.user.User;
import com.simplebank.domain.exceptions.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FindTransactionUsersUseCase {

    @Autowired
    private FindUserByDocumentUseCase findUserByDocumentUseCase;

    public List<User> call(String senderDocument, String receiverDocument) throws UserNotFoundException {

        Optional<User> senderOptional = findUserByDocumentUseCase.call(senderDocument);
        if (senderOptional.isEmpty()) {
            throw new UserNotFoundException("Remetente não encontrado");
        }
        User sender = senderOptional.get();

        Optional<User> receiverOptional = findUserByDocumentUseCase.call(receiverDocument);
        if (receiverOptional.isEmpty()) {
            throw new UserNotFoundException("Receptor não encontrado");
        }
        User receiver = receiverOptional.get();

        return List.of(sender, receiver);
    }
}
