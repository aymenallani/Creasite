package com.Projet.usermanagement.entity;

import com.Projet.base.entity.BaseEntity;

import jakarta.persistence.Entity;
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


}