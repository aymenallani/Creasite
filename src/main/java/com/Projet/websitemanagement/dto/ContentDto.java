package com.Projet.websitemanagement.dto;


import com.Projet.websitemanagement.entity.EditableRegion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentDto {
	
	private String content;
	private EditableRegion editableRegion;

}
