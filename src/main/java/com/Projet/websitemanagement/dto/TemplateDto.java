package com.Projet.websitemanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateDto {
	private long id; 
	private String name;
	private String description;
	private String content;
}