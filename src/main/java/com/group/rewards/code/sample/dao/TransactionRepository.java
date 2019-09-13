package com.group.rewards.code.sample.dao;

import com.group.rewards.code.sample.domain.Customer;
import com.group.rewards.code.sample.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
	List<Transaction> findByCustomerAndCreatedDateBetweenAndTransactionAmountGreaterThan(Customer customer,
																						 Date startDateTime,
																						 Date endDateTime,
																						 Integer amount);
}
