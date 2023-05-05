package com.Projet.websitemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Projet.websitemanagement.dto.TemplateDto;
import com.Projet.websitemanagement.entity.Template;
import com.Projet.websitemanagement.mapper.TemplateMapper;
import com.Projet.websitemanagement.service.TemplateService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/templates")
public class TemplateController {
	
	@Autowired
	private TemplateService TemplateService;
	@Autowired
	private TemplateMapper TemplateMapper;
	
	@GetMapping()
	public ResponseEntity<?> findAll() {
		
		List<Template> templates = TemplateService.findAll();
		
		List<TemplateDto> dtos = TemplateMapper.map(templates);

		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id){

		Template template = TemplateService.findById(id);

		TemplateDto dto = TemplateMapper.map(template);

		return ResponseEntity.ok(dto);
	}


}
