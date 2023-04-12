package com.Projet.usermanagement.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.Projet.base.repository.BaseRepository;
import com.Projet.usermanagement.entity.AppUser;

@Repository
public interface UserRepository extends BaseRepository <AppUser, Long> {
	
	Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmail(String email);

}
