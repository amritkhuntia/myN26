package com.amrit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amrit.entity.Transaction;

public interface StatisticsRepository extends JpaRepository<Transaction, Long> {
	
	
	@Query("select time,amount from Transaction t where ?1 - t.time=60000")
	public List<Transaction> findAllBefore(long systemTime);

}
