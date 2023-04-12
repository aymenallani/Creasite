package com.Projet.websitemanagement.entity;

import com.Projet.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sections")
public class Section extends BaseEntity{
	
	private String name;
		
	@ManyToOne()
	@JoinColumn(name = "website_id")
	@JsonIgnore
	private Website website;
	
	@ManyToOne()
	@JoinColumn(name = "templateSection_id")
	private TemplateSection templatesection;

}
