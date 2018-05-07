package com.amrit.endpoint;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amrit.entity.Transaction;
import com.amrit.repository.TransactionRepository;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionRestController {

	@Autowired
	private TransactionRepository transactionRepo;

	@RequestMapping(value = "/v1.0", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity addTransactionInfo(@RequestBody Transaction transaction) {
		try {
			int TIME_LIMIT=60000;
			if (Instant.now().toEpochMilli()-transaction.getTime() < TIME_LIMIT) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			transactionRepo.save(transaction);
			return new ResponseEntity<Transaction>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Transaction>(HttpStatus.NO_CONTENT);
		}
	}
}
