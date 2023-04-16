package com.Projet.usermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
	@NotBlank
	@Size(max = 50)
	private String username;
	@Email
	private String email;
	@NotBlank
	@Size(min = 6, max = 100)
	private String password;
	

}
