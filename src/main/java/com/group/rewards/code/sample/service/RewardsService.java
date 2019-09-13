package com.group.rewards.code.sample.service;

import com.group.rewards.code.sample.dao.CustomerRepository;
import com.group.rewards.code.sample.dao.TransactionRepository;
import com.group.rewards.code.sample.domain.Customer;
import com.group.rewards.code.sample.domain.Transaction;
import com.group.rewards.code.sample.dto.ErrorResponseDTO;
import com.group.rewards.code.sample.dto.ResponseDTO;
import com.group.rewards.code.sample.dto.RewardRequestDTO;
import com.group.rewards.code.sample.dto.RewardResponseDTO;
import com.group.rewards.code.sample.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RewardsService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Value("${min.dollar.eligible.min.rewardpoints}")
	private int amtEligibleForMinRewardPoints;

	@Value("${min.dollar.eligible.max.rewardpoints}")
	private int amtEligibleForMaxRewardPoints;

	@Value("${max.rewardpoints}")
	private int maxRewardPoints;

	@Value("${min.rewardpoints}")
	private int minRewardPoints;

	public ResponseDTO getRewardPoints(String customerId, RewardRequestDTO rewardRequestDTO) {
		ResponseDTO responseDTO;
		Date fromDate;
		Date toDate;
		try {
			fromDate = DateUtils.convertStringToDate(rewardRequestDTO.getFromDate());
			toDate = DateUtils.convertStringToDate(rewardRequestDTO.getToDate());
		} catch (ParseException parseEx) {
			// record the exception
			return new ErrorResponseDTO("FAILED", "Invalid date time format.");
		}

		Optional<Customer> customerOptional = customerRepository.findById(customerId);
		if (customerOptional.isPresent()) {
			Customer customer = customerOptional.get();
			List<Transaction> transactionsList = transactionRepository.findByCustomerAndCreatedDateBetweenAndTransactionAmountGreaterThan(customer,
					fromDate, toDate, amtEligibleForMinRewardPoints);
			int rewardPoints = processRewards(transactionsList);
			responseDTO = new RewardResponseDTO("SUCCESSFUL", customer.getId(), customer.getCustomerName(), Integer.toString(rewardPoints));
		} else {
			responseDTO = new ErrorResponseDTO("FAILED", "Customer couldn't be found");
		}

		return responseDTO;
	}

	private int processRewards(List<Transaction> transactionList) {
		int totalRewardPoints = 0;

		for(Transaction transaction : transactionList) {
			int rewardPoints = calculatePoints(transaction);
			totalRewardPoints += rewardPoints;
		}

		return totalRewardPoints;
	}

	private int calculatePoints(Transaction transaction) {
		int eligibleAmountForMaxPoints = 0;
		int transactionAmount = Math.round(transaction.getTransactionAmount());

		if (transactionAmount > amtEligibleForMaxRewardPoints) {
			// amount eligible for earning max. reward points
			eligibleAmountForMaxPoints = transactionAmount - (amtEligibleForMaxRewardPoints);
		}

		// amount eligible for earning min. reward points
		int eligibleAmountForMinPoints = transactionAmount - eligibleAmountForMaxPoints - (amtEligibleForMinRewardPoints);

		return (eligibleAmountForMaxPoints * maxRewardPoints) + (eligibleAmountForMinPoints * minRewardPoints);
	}
}
