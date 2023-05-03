package com.Projet.usermanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Projet.base.service.BaseService;
import com.Projet.error.DataNotFoundException;
import com.Projet.security.AppUserDetailsService;
import com.Projet.security.JwtService;
import com.Projet.usermanagement.dto.AuthentificationDto;
import com.Projet.usermanagement.dto.RegistrationDto;
import com.Projet.usermanagement.entity.AppUser;
import com.Projet.usermanagement.entity.AuthenticationResponse;
import com.Projet.usermanagement.repository.UserRepository;
import com.Projet.websitemanagement.entity.Template;

@Service
public class UserService  extends BaseService<AppUser, Long>{
	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private  AppUserDetailsService myUserDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;

	
	public AuthenticationResponse Registration(RegistrationDto registrationDto) {
		var user = AppUser.builder()
				.username(registrationDto.getUsername())
				.email(registrationDto.getEmail())
				.password(registrationDto.getPassword())
				.roles("USER_ROLE")
				.build();
		try {
			 userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Duplicate username or email");
		} catch (Exception e) {
			throw new RuntimeException("Failed to register user");
		}
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(registrationDto.getUsername());
		var jwtToken = jwtService.generateToken(userDetails);
		return AuthenticationResponse.builder()
			        .token(jwtToken)
			        .build();
		
	}
	
	 public AuthenticationResponse authenticate(AuthentificationDto authenticationDto) {
		    authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(
		        		authenticationDto.getUsername(),
		        		authenticationDto.getPassword()
		        )
		    );
		    var user = userRepository.findByUsername(authenticationDto.getUsername())
		        .orElseThrow();
			UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationDto.getUsername());
		    var jwtToken = jwtService.generateToken(userDetails);
		    return AuthenticationResponse.builder()
			        .token(jwtToken)
			        .build();
		  }
	
	public AppUser findById(Long id) {
		Optional<AppUser> optionalAppUser = userRepository.findById(id);
		AppUser user = optionalAppUser.orElseThrow(() -> new DataNotFoundException("user not found"));
        return user;
	}
}
