package com.Projet.websitemanagement.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Projet.websitemanagement.dto.AddContentRequest;
import com.Projet.websitemanagement.dto.ContentDto;
import com.Projet.websitemanagement.dto.SectionDto;
import com.Projet.websitemanagement.dto.WebsiteDto;
import com.Projet.websitemanagement.entity.Content;
import com.Projet.websitemanagement.entity.Section;
import com.Projet.websitemanagement.entity.Website;
import com.Projet.websitemanagement.mapper.SectionMapper;
import com.Projet.websitemanagement.mapper.ContentMapper;
import com.Projet.websitemanagement.repository.SectionRepository;
import com.Projet.websitemanagement.service.AddSectionWithContentsService;
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
	@Autowired
	private AddSectionWithContentsService addSectionWithContentsService;
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSectionById(@PathVariable("id") Long id) {
		sectionService.deleteSectionById(id);
        return ResponseEntity.ok().build();
    }
	
	@PostMapping("/{sectionID}")
	public ResponseEntity<?> addContent(@PathVariable long sectionID, @RequestBody AddContentRequest request){
		
		return ResponseEntity.ok(sectionService.addContent(sectionID,request.getEditableRegionID(), request.getContent()));
		 
	}
	
	@PostMapping("/addSection/{websiteID}")
	public ResponseEntity<?> addSectionWithContents(@PathVariable long websiteID, @RequestBody String jsonData){
		 try {
		return ResponseEntity.ok(addSectionWithContentsService.addSectionWithContents(websiteID,jsonData));
		 } catch (IOException e) {
		        throw new RuntimeException("Failed to save content", e);
		    }
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Section section = sectionService.findById(id);
        SectionDto dto = sectionMapper.map(section);
        return ResponseEntity.ok(dto);
	}

}
