package com.Projet.websitemanagement.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Projet.error.DataNotFoundException;
import com.Projet.usermanagement.entity.AppUser;
import com.Projet.usermanagement.repository.UserRepository;
import com.Projet.websitemanagement.classMapesJSON.MySection;
import com.Projet.websitemanagement.classMapesJSON.NewContent;
import com.Projet.websitemanagement.entity.EditableRegion;
import com.Projet.websitemanagement.entity.Section;
import com.Projet.websitemanagement.entity.TemplateSection;
import com.Projet.websitemanagement.entity.Website;
import com.Projet.websitemanagement.repository.EditableRegionRepository;
import com.Projet.websitemanagement.repository.SectionRepository;
import com.Projet.websitemanagement.repository.TemplateSectionRepository;
import com.Projet.websitemanagement.repository.WebsiteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class AddSectionWithContentsService {
	
	@Autowired
	private WebsiteService websiteService;
	@Autowired
	private WebsiteRepository websiteRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SectionRepository sectionRepository;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private TemplateSectionRepository templateSectionRepository;
	@Autowired
	private EditableRegionRepository editableRegionRepository;
	
	@Transactional
	public Website addSectionWithContents (long websiteID, String jsonData) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		MySection mySection = objectMapper.readValue(jsonData, MySection.class);
		Section newSection = new Section();
		newSection.setName(mySection.getSectionName());
		Optional<TemplateSection> optionalTemplateSection = templateSectionRepository.findById(mySection.getTemplateSectionID());
        TemplateSection templateSection = optionalTemplateSection.orElseThrow(() -> new DataNotFoundException("Template Section not found"));
        newSection.setTemplatesection(templateSection);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("User not authenticated");
        }

        // Get the authenticated user's username and retrieve the user from the database
        String username = authentication.getName();
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        if (!user.getSubscription().getStatus().equals("ACTIVE")) {
        	throw new AccessDeniedException("Subscription not active ");
        }
        
        
	    Website website = websiteRepository.findById(websiteID).orElseThrow(() -> new DataNotFoundException("Website not found"));

        if (!website.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("User is not authorized to add Section to this website");
        }
        
        if (user.getSubscription().getType().equals("Gratuit") && website.getSections().size() == 5) {
        	throw new AccessDeniedException("Maximum section limit reached");
        }
        if (user.getSubscription().getType().equals("Basique") && website.getSections().size() == 8) {
        	throw new AccessDeniedException("Maximum section limit reached");
        }
        
        newSection.setWebsite(website);
        sectionRepository.save(newSection);
        List<Section> websections = website.getSections();
        websections.add(newSection);
        website.setSections(websections);
        websiteRepository.save(website);

        
		for (NewContent newContent : mySection.getNewContent()) {
		    Long editableRegionsID = newContent.getEditableRegionsID();
		    String content = newContent.getContent();
	        
	        sectionService.addContent(newSection.getId(), editableRegionsID, content);
		}
	    return website;

	}

}
