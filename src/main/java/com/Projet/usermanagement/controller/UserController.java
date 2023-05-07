package com.Projet.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Projet.usermanagement.dto.AuthentificationDto;
import com.Projet.usermanagement.dto.RegistrationDto;
import com.Projet.usermanagement.dto.UserDto;
import com.Projet.usermanagement.entity.AppUser;
import com.Projet.usermanagement.entity.AuthenticationResponse;
import com.Projet.usermanagement.mapper.RegistrationMapper;
import com.Projet.usermanagement.mapper.UserMapper;
import com.Projet.usermanagement.service.UserService;
import com.Projet.websitemanagement.dto.TemplateDto;
import com.Projet.websitemanagement.entity.Template;
import com.Projet.usermanagement.dto.TokenValidDto;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RegistrationMapper registrationMapper;
	@Autowired
	private UserMapper userMapper;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
		      @RequestBody RegistrationDto request
		  ) {
		    return ResponseEntity.ok(userService.Registration(request));
	}
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
        @RequestBody AuthentificationDto request
    ) {
      return ResponseEntity.ok(userService.authenticate(request));
    }
    
    @PostMapping("TokenValid")
	public ResponseEntity<?> TokenValid(@RequestParam String token){
    	
    	TokenValidDto tokenValidDto = new TokenValidDto();
    	tokenValidDto.setValid(userService.TokenValid(token));

		return ResponseEntity.ok(tokenValidDto);
	}
    
    @PostMapping("TokenInfo")
    public ResponseEntity<?> TokenInfo(@RequestParam String token) {
    	UserDto userDto = new UserDto();
    	userDto.setUsername(userService.TokenInfo(token));
    	return ResponseEntity.ok(userDto);
	 }
	
	@GetMapping("users/{id}")
	public ResponseEntity<?> findById(@PathVariable long id){

		AppUser user = userService.findById(id);

		UserDto dto = userMapper.map(user);

		return ResponseEntity.ok(dto);
	}

}






