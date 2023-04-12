package com.Projet.usermanagement.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private String username;
	@Email
	private String email;

}