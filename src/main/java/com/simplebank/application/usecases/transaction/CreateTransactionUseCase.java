package com.simplebank.application.usecases.transaction;

import com.simplebank.application.services.AuthorizationServiceImpl;
import com.simplebank.application.services.NotificationServiceImpl;
import com.simplebank.application.usecases.user.FindTransactionUsersUseCase;
import com.simplebank.application.usecases.user.UpdateUserBalancesUseCase;
import com.simplebank.domain.dtos.TransactionDTO;
import com.simplebank.domain.entities.transaction.Transaction;
import com.simplebank.domain.entities.user.User;
import com.simplebank.domain.exceptions.authorization.AuthorizationException;
import com.simplebank.data.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class CreateTransactionUseCase {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AuthorizationServiceImpl authorizationService;
    @Autowired
    private NotificationServiceImpl notificationService;

    @Autowired
    private FindTransactionUsersUseCase findTransactionUsersUseCase;

    @Autowired
    private UpdateUserBalancesUseCase updateUserBalancesUseCase;

    public Transaction call(TransactionDTO transactionDTO) throws Exception {

        List<User> users = findTransactionUsersUseCase.call(transactionDTO.senderDocument(), transactionDTO.receiverDocument());

        boolean isAuthorized = authorizationService.authorizeTransaction();
        if (!isAuthorized) {
            throw new AuthorizationException("Erro inesperado, por favor tente novamente.");

        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDTO.amount());
        newTransaction.setSender(users.get(0));
        newTransaction.setReceiver(users.get(1));
        newTransaction.setTimeStamp(LocalDateTime.now());



        this.notificationService.sendNotification(users.get(0), "Transação realizada com sucesso");
        this.notificationService.sendNotification(users.get(1), "Transação recebida com sucesso");
        updateUserBalancesUseCase.call(users, transactionDTO.amount());
        this.transactionRepository.save(newTransaction);

        return newTransaction;
    }
}

