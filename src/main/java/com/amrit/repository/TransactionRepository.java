package com.amrit.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amrit.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
