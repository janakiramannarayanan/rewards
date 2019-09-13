package com.group.rewards.code.sample.dao;

import com.group.rewards.code.sample.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
	Optional<Customer> findById(String id);
}
