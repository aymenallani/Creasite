package com.Projet.usermanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Projet.base.service.BaseService;
import com.Projet.error.DataNotFoundException;
import com.Projet.usermanagement.entity.AppUser;
import com.Projet.usermanagement.repository.UserRepository;
import com.Projet.websitemanagement.entity.Template;

@Service
public class UserService  extends BaseService<AppUser, Long>{
	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	
	public AppUser Registration(AppUser user) {
		AppUser newUser = new AppUser();
		newUser.setUsername(user.getUsername());
		newUser.setEmail(user.getEmail()); 
		newUser.setPassword(user.getPassword());
		//newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setRoles("USER_ROLE");
		try {
			return userRepository.save(newUser);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Duplicate username or email");
		} catch (Exception e) {
			throw new RuntimeException("Failed to register user");
		}
	}
	
	public AppUser findById(Long id) {
		Optional<AppUser> optionalAppUser = userRepository.findById(id);
		AppUser user = optionalAppUser.orElseThrow(() -> new DataNotFoundException("user not found"));
        return user;
	}
}
