package com.Projet.usermanagement.entity;

import com.Projet.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class AppUser extends BaseEntity {
	@Column(unique=true)
	@NotBlank
	@Size(max = 50)
	private String username;
	@Email
	@Column(unique=true)
	private String email;
	@NotBlank
	@Size(min = 6, max = 100)
	private String password;
	private String roles;
	
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Subscription subscription;


}