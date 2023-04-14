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
@Table(name = "Contents")
public class Content extends BaseEntity{
	
	private String content;
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "section_id")
	private Section section;
	
	@ManyToOne()
	@JoinColumn(name = "editableRegions_id")
	private EditableRegion editableRegion;

}
