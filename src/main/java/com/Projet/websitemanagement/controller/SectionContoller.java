package com.Projet.websitemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Projet.websitemanagement.dto.ContentDto;
import com.Projet.websitemanagement.dto.SectionDto;
import com.Projet.websitemanagement.dto.WebsiteDto;
import com.Projet.websitemanagement.entity.Content;
import com.Projet.websitemanagement.entity.Section;
import com.Projet.websitemanagement.entity.Website;
import com.Projet.websitemanagement.mapper.SectionMapper;
import com.Projet.websitemanagement.mapper.ContentMapper;
import com.Projet.websitemanagement.repository.SectionRepository;
import com.Projet.websitemanagement.service.SectionService;

@RestController
@RequestMapping("/sections")
public class SectionContoller {
	
	@Autowired
	private SectionMapper sectionMapper;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private ContentMapper contentMapper;
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSectionById(@PathVariable("id") Long id) {
		sectionService.deleteSectionById(id);
        return ResponseEntity.ok().build();
    }
	
	@PutMapping("/{sectionID}")
	public ResponseEntity<?> addContent(@PathVariable long sectionID, @RequestBody ContentDto contentdto){
		Content content = contentMapper.unMap(contentdto);
		return ResponseEntity.ok(sectionService.addContent(sectionID, content));
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Section section = sectionService.findById(id);
        SectionDto dto = sectionMapper.map(section);
        return ResponseEntity.ok(dto);
	}

}
