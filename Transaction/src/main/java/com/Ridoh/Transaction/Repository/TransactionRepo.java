package com.Ridoh.Transaction.Repository;

import com.Ridoh.Transaction.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, String> {
}
