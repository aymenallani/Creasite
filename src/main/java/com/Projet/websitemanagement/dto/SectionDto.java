package com.Projet.websitemanagement.dto;

import java.util.List;

import com.Projet.websitemanagement.entity.Content;
import com.Projet.websitemanagement.entity.TemplateSection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionDto {
	
	private String name;
	
	private TemplateSection templatesection;

	
	private List<Content> contents;
}                                                                 
