package com.Projet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Projet.usermanagement.entity.SecurityUser;
import com.Projet.usermanagement.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        return userRepository
	                .findByUsername(username)
	                .map(SecurityUser::new)
	                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
	    }

}
