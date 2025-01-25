package com.simplebank.api.controllers;
import com.simplebank.application.usecases.transaction.CreateTransactionUseCase;
import com.simplebank.application.usecases.transaction.FindAllTransactionsUseCase;
import com.simplebank.domain.dtos.TransactionDTO;
import com.simplebank.domain.entities.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private CreateTransactionUseCase createTransaction;
    @Autowired
    private FindAllTransactionsUseCase findAllTransactionsUseCase;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception{
        Transaction newTransaction = createTransaction.call(transactionDTO);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Optional<List<Transaction>>> getAll() throws Exception {
        Optional<List<Transaction>> transactions = findAllTransactionsUseCase.call();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

}
