package com.group.rewards.code.sample.controller;

import com.group.rewards.code.sample.dto.ResponseDTO;
import com.group.rewards.code.sample.dto.RewardRequestDTO;
import com.group.rewards.code.sample.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/creditcard")
public class RewardsController {

	@Autowired
	private RewardsService rewardsService;

	@GetMapping(value = "/rewardpoints/{customerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO getRewardPoints(@PathVariable("customerId") String customerId,
									   @RequestBody RewardRequestDTO rewardRequestDTO) {
		return rewardsService.getRewardPoints(customerId, rewardRequestDTO);
	}
}
