package com.Projet.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Projet.usermanagement.dto.RegistrationDto;
import com.Projet.usermanagement.entity.AppUser;
import com.Projet.usermanagement.mapper.RegistrationMapper;
import com.Projet.usermanagement.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RegistrationMapper registrationMapper;
	
	@PostMapping("/register")
	public ResponseEntity<AppUser> registerUser(@Valid @RequestBody RegistrationDto userDto) {
		AppUser user = registrationMapper.unMap(userDto);
		return ResponseEntity.ok(userService.Registration(user));
	}

}






