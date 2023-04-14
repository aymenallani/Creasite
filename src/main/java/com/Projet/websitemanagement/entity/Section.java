package com.Projet.websitemanagement.entity;

import java.util.List;

import com.Projet.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "sections")
public class Section extends BaseEntity{
	
	private String name;
		
	@ManyToOne()
	@JoinColumn(name = "website_id")
	@JsonIgnore
	private Website website;
	
	@OneToMany(mappedBy = "section", cascade = CascadeType.REMOVE)
	private List<Content> contents;
	
	@ManyToOne()
	@JoinColumn(name = "templateSection_id")
	private TemplateSection templatesection;

}
