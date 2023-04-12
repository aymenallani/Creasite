package com.Projet.websitemanagement.dto;

import java.util.List;

import com.Projet.websitemanagement.entity.Section;
import com.Projet.websitemanagement.entity.Template;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebsiteDto {
	private String name;
	private String url;
	private List<Section> sections;
	private Template template;
}
