package com.Ridoh.Transaction.Service;

import com.Ridoh.Transaction.Dto.Response;
import com.Ridoh.Transaction.Dto.TransactionDto;
import com.Ridoh.Transaction.Dto.TransactionRequest;
import com.Ridoh.Transaction.Dto.TransferRequest;
import com.Ridoh.Transaction.Entity.Transaction;
import com.Ridoh.Transaction.Repository.TransactionRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepo transactionRepo;

    public TransactionServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public void saveTransaction(TransactionDto transaction) {
        Transaction newTransaction = Transaction.builder()
                .transactionType(transaction.getAccountNumber())
                .accountNumber(transaction.getAccountNumber())
                .amount(transaction.getAmount())
                .build();

        transactionRepo.save(newTransaction);

    }
}