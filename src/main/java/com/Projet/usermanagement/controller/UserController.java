package com.Projet.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Projet.usermanagement.dto.RegistrationDto;
import com.Projet.usermanagement.dto.UserDto;
import com.Projet.usermanagement.entity.AppUser;
import com.Projet.usermanagement.mapper.RegistrationMapper;
import com.Projet.usermanagement.mapper.UserMapper;
import com.Projet.usermanagement.service.UserService;
import com.Projet.websitemanagement.dto.TemplateDto;
import com.Projet.websitemanagement.entity.Template;

import jakarta.validation.Valid;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RegistrationMapper registrationMapper;
	@Autowired
	private UserMapper userMapper;
	
    @CrossOrigin(origins = "http://localhost:4200/")
	@PostMapping("/register")
	public ResponseEntity<AppUser> registerUser(@Valid @RequestBody RegistrationDto userDto) {
		AppUser user = registrationMapper.unMap(userDto);
		return ResponseEntity.ok(userService.Registration(user));
	}
	
	@GetMapping("users/{id}")
	public ResponseEntity<?> findById(@PathVariable long id){

		AppUser user = userService.findById(id);

		UserDto dto = userMapper.map(user);

		return ResponseEntity.ok(dto);
	}

}






