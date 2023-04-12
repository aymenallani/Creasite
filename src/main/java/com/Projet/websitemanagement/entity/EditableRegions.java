package com.Projet.websitemanagement.entity;

import com.Projet.base.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "editableRegions")
public class EditableRegions extends BaseEntity{
	
	private String name;
	private String Type;
	private int multiplicated;
	private String groupe;
	
	@ManyToOne()
	@JoinColumn(name = "templateSection_id")
	private TemplateSection templatesection;
}
