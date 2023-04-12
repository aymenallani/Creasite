package com.Projet.websitemanagement.entity;

import java.util.List;

import com.Projet.base.entity.BaseEntity;
import com.Projet.usermanagement.entity.AppUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "websites")
public class Website extends BaseEntity{
	
	private String name;
	private String url;
	
	@OneToMany(mappedBy = "website", cascade = CascadeType.REMOVE)
	private List<Section> sections;
	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private AppUser user;
	
	@ManyToOne()
	@JoinColumn(name = "template_id")
	private Template template;

}
