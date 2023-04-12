package com.Projet.websitemanagement.entity;

import com.Projet.base.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "templateSections")
public class TemplateSection extends BaseEntity{
	private String name;
	private int nbrEditReg;
	@OneToOne()
	@JoinColumn(name = "template_id")
	private Template template;

}
