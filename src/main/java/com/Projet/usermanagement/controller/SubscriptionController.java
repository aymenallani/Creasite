package com.Projet.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Projet.usermanagement.dto.SubscriptionDto;
import com.Projet.usermanagement.entity.Subscription;
import com.Projet.usermanagement.mapper.SubscriptionMapper;
import com.Projet.usermanagement.service.SubscriptionService;


@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	@Autowired
	private SubscriptionMapper subscriptionMapper;
	
	@PostMapping("")
	public ResponseEntity<?> createSubscription(@RequestBody SubscriptionDto subscriptiondto){
		Subscription subscription = subscriptionMapper.unMap(subscriptiondto);
		return ResponseEntity.ok(subscriptionService.createSubscription(subscription));
	}

}
