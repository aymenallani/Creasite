package com.Projet.usermanagement.entity;

import com.Projet.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class AppUser extends BaseEntity {
	
	private String username;
	@Email
	private String email;
	private String password;
	private String roles;
	
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Subscription subscription;


}