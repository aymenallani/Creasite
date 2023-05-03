package com.Projet.usermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthentificationDto {
	
	@NotBlank
	@Size(max = 50)
	private String username;
	
	@NotBlank
	@Size(min = 6, max = 100)
	private String password;

}
