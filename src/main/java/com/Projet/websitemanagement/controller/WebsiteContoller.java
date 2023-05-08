package com.Projet.websitemanagement.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Projet.usermanagement.entity.Subscription;
import com.Projet.websitemanagement.dto.SectionDto;
import com.Projet.websitemanagement.dto.TemplateDto;
import com.Projet.websitemanagement.dto.WebsiteDto;
import com.Projet.websitemanagement.entity.Section;
import com.Projet.websitemanagement.entity.Website;
import com.Projet.websitemanagement.mapper.SectionMapper;
import com.Projet.websitemanagement.mapper.WebsiteByUserIdMapper;
import com.Projet.websitemanagement.mapper.WebsiteMapper;
import com.Projet.websitemanagement.repository.TemplateRepository;
import com.Projet.websitemanagement.service.TemplateService;
import com.Projet.websitemanagement.service.WebsiteService;
import com.Projet.websitemanagement.dto.WebsiteByUserIdDto;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/websites")
public class WebsiteContoller {
	
	@Autowired
	private TemplateRepository templateRepository;
	@Autowired
	private WebsiteService websiteService;
	@Autowired
	private WebsiteMapper websiteMapper;
	@Autowired
	private SectionMapper sectionMapper;
	@Autowired
	private WebsiteByUserIdMapper websiteByUserIdMapper;
	
	@PostMapping("")
	public ResponseEntity<?> createWebsite(@RequestBody WebsiteDto websiteDto){
		Website website = websiteMapper.unMap(websiteDto);
		return ResponseEntity.ok(websiteService.createWebsite(website));
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWebsiteById(@PathVariable("id") Long id) {
        websiteService.deleteWebsiteById(id);
        return ResponseEntity.ok().build();
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Website website = websiteService.findById(id);
        WebsiteDto dto = websiteMapper.map(website);
        return ResponseEntity.ok(dto);
    }
	
	@GetMapping("/user/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable Long id) {
	    List<Website> Websites = websiteService.findByUserId(id);
	    List<WebsiteByUserIdDto> dto = websiteByUserIdMapper.map(Websites);
        return ResponseEntity.ok(dto);
    }
	
	@PostMapping("/{websiteID}")
	public ResponseEntity<?> addSection(@PathVariable long websiteID, @RequestBody SectionDto sectiondto){
		Section section = sectionMapper.unMap(sectiondto);
		return ResponseEntity.ok(websiteService.addSection(websiteID, section));
	}
	
	@PutMapping("/{websiteID}/update-name")
	public ResponseEntity<?> updateName(@PathVariable long websiteID, @RequestBody String Name){
	    return ResponseEntity.ok(websiteService.updateName(websiteID, Name));
	}

}
