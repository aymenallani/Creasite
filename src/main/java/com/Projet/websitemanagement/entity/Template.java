package com.Projet.websitemanagement.entity;

import com.Projet.base.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "templates")
public class Template extends BaseEntity{
	private String name;
	private String description;
}