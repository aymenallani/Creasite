package com.Projet.websitemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Projet.websitemanagement.dto.TemplateSectionDto;
import com.Projet.websitemanagement.entity.EditableRegion;
import com.Projet.websitemanagement.entity.TemplateSection;
import com.Projet.websitemanagement.mapper.TemplateSectionMapper;
import com.Projet.websitemanagement.service.EditableRegionService;
import com.Projet.websitemanagement.service.TemplateSectionService;

@RestController
@RequestMapping("/editableRegions")
public class EditableRegionController {
	
	@Autowired
	private EditableRegionService editableRegionService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id){

		EditableRegion editableRegion = editableRegionService.findById(id);

		return ResponseEntity.ok(editableRegion);
	}
	
	@GetMapping("/templateSection/{SectionId}")
	public ResponseEntity<?> findBySectionId(@PathVariable long SectionId){

		List<EditableRegion> editableRegions = editableRegionService.findByTemplateSectionId(SectionId);

		return ResponseEntity.ok(editableRegions);
	}
	

}
