package com.Ridoh.Transaction.Service;

import com.Ridoh.Transaction.Dto.Response;
import com.Ridoh.Transaction.Dto.TransactionDto;
import com.Ridoh.Transaction.Dto.TransactionRequest;
import com.Ridoh.Transaction.Dto.TransferRequest;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    void saveTransaction(TransactionDto transactionDto);
//List<TransactionDto>fetchAllTransaction()

//    ResponseEntity<Response> nameEnquiry(String accountNumber);
//
//    ResponseEntity<Response> credit(TransactionRequest transactionRequest);
//
//    ResponseEntity<Response> debit(TransactionRequest transactionRequest);
//
//    ResponseEntity<Response> transfer(TransferRequest transferRequest);
}
