package com.example.MAMAPhone.repositories;

import com.example.MAMAPhone.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    /*Transaction findByIdTransaction(Long id);*/
}