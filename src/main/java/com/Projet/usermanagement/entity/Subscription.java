package com.Projet.usermanagement.entity;

import java.time.LocalDate;

import com.Projet.base.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "subscriptions")
public class Subscription extends BaseEntity{
	
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
	private String type;
	private String PaymentMethod;
	private int AmountPaid;
	@OneToOne()
	@JoinColumn(name = "user_id")
	private AppUser user;
	

}
