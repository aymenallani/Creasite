package com.Projet.websitemanagement.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Projet.websitemanagement.dto.ContentDto;
import com.Projet.websitemanagement.mapper.ContentMapper;
import com.Projet.websitemanagement.entity.Content;
import com.Projet.websitemanagement.service.ContentService;

@RestController
@RequestMapping("/contents")
public class ContentController {
	@Autowired
	private ContentService contentService;
	@Autowired
	private ContentMapper contentMapper;
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContentById(@PathVariable("id") Long id) {
		contentService.deleteContentById(id);
        return ResponseEntity.ok().build();
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
		Content content = contentService.findById(id);
		ContentDto dto = contentMapper.map(content);
        return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/{contentId}")
	public ResponseEntity<?> updateContent(@PathVariable long contentId, @RequestBody String Content, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile){
		try {
	    return ResponseEntity.ok(contentService.updateContent(contentId, Content, imageFile));
		} catch (IOException e) {
	        throw new RuntimeException("Failed to save file", e);
	    }
	}

}
