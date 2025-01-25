package com.simplebank.application.usecases.transaction;

import com.simplebank.domain.entities.transaction.Transaction;
import com.simplebank.data.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FindAllTransactionsUseCase {
    @Autowired
    private TransactionRepository transactionRepository;

    public Optional<List<Transaction>> call() throws Exception {
        Optional<List<Transaction>> transactions = Optional.of(this.transactionRepository.findAll());
        if(transactions.isPresent()){
            return transactions;
        } else {
            throw new Exception("Nenhuma transação encontrada.");
        }

    }
}
