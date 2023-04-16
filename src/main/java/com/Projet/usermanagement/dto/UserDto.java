package com.Projet.usermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	@NotBlank
	@Size(max = 50)
	private String username;
	@Email
	private String email;

}