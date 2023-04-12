package com.Projet.usermanagement.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionDto {
	private LocalDate StartDate;
	private LocalDate EndDate;
	private String status;
	private String type;
	private String PaymentMethod;
	private int AmountPaid;

}
