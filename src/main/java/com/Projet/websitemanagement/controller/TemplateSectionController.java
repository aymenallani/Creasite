package com.Projet.websitemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Projet.websitemanagement.dto.TemplateDto;
import com.Projet.websitemanagement.dto.TemplateSectionDto;
import com.Projet.websitemanagement.entity.Template;
import com.Projet.websitemanagement.entity.TemplateSection;
import com.Projet.websitemanagement.mapper.TemplateSectionMapper;
import com.Projet.websitemanagement.service.TemplateSectionService;

@RestController
@RequestMapping("/templateSections")
public class TemplateSectionController {
	
	@Autowired
	private TemplateSectionService templateSectionService;
	@Autowired
	private TemplateSectionMapper templateSectionMapper;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id){

		TemplateSection templateSection = templateSectionService.findById(id);

		TemplateSectionDto dto = templateSectionMapper.map(templateSection);

		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/website/{WebsiteId}")
	public ResponseEntity<?> findByWebsiteId(@PathVariable long WebsiteId){

		List<TemplateSection> templateSections = templateSectionService.findByWebsiteId(WebsiteId);

		List<TemplateSectionDto> dtos = templateSectionMapper.map(templateSections);

		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/template/{TemplateId}")
	public ResponseEntity<?> findByTemplateId(@PathVariable long TemplateId){

		List<TemplateSection> templateSections = templateSectionService.findByTemplateId(TemplateId);

		return ResponseEntity.ok(templateSections);
	}


}
