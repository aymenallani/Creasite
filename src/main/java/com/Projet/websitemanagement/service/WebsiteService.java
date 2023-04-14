package com.Projet.websitemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Projet.base.service.BaseService;
import com.Projet.usermanagement.entity.AppUser;
import com.Projet.usermanagement.repository.UserRepository;
import com.Projet.websitemanagement.entity.Section;
import com.Projet.websitemanagement.entity.Template;
import com.Projet.websitemanagement.entity.TemplateSection;
import com.Projet.websitemanagement.entity.Website;
import com.Projet.websitemanagement.repository.SectionRepository;
import com.Projet.websitemanagement.repository.TemplateRepository;
import com.Projet.websitemanagement.repository.TemplateSectionRepository;
import com.Projet.websitemanagement.repository.WebsiteRepository;

import jakarta.transaction.Transactional;

@Service
public class WebsiteService  extends BaseService<Website, Long>{
	
	@Autowired
	private WebsiteRepository websiteRepository;
	@Autowired
	private TemplateRepository templateRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TemplateSectionRepository templateSectionRepository;
	@Autowired
	private SectionRepository sectionRepository;
	
	
	@Transactional
	public Website createWebsite(Website newWebsite) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }

        // Get the authenticated user's username and retrieve the user from the database
        String username = authentication.getName();
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getSubscription().getStatus().equals("ACTIVE")) {
        	throw new RuntimeException("Subscription not active ");
        }
        Website site = new Website();
        site.setUser(user);
        if (newWebsite.getName() == null || newWebsite.getName().isEmpty()) {
            throw new IllegalArgumentException("Website name cannot be null or empty.");
        }
        site.setName(newWebsite.getName());  
        Optional<Template> optionalTemplate = templateRepository.findById(newWebsite.getTemplate().getId());
        Template template = optionalTemplate.orElseThrow(() -> new RuntimeException("Template not found"));
        site.setTemplate(template);
        
        return websiteRepository.save(site);
	}
	
	@Transactional
	public void deleteWebsiteById(Long websiteId) {
	    Website website = websiteRepository.findById(websiteId).orElseThrow(() -> new RuntimeException("Website not found"));
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }
        String username = authentication.getName();
        if (!website.getUser().getUsername().equals(username)) {
            throw new RuntimeException("User is not authorized to delete this website");
        }
	    websiteRepository.delete(website);
	}
	
	public Website findById(Long id) {
		Optional<Website> optionalWebsite = websiteRepository.findById(id);
		Website website = optionalWebsite.orElseThrow(() -> new RuntimeException("Website not found"));
		return website;
	}
	
	@Transactional
	public Website addSection (long websiteID, Section section) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }

        // Get the authenticated user's username and retrieve the user from the database
        String username = authentication.getName();
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!user.getSubscription().getStatus().equals("ACTIVE")) {
        	throw new RuntimeException("Subscription not active ");
        }
        
        
	    Website website = websiteRepository.findById(websiteID).orElseThrow(() -> new RuntimeException("Website not found"));

        if (!website.getUser().getUsername().equals(username)) {
            throw new RuntimeException("User is not authorized to add Section to this website");
        }
        
        if (user.getSubscription().getType().equals("Gratuit") && website.getSections().size() == 5) {
        	throw new RuntimeException("Maximum section limit reached");
        }
        if (user.getSubscription().getType().equals("Basique") && website.getSections().size() == 8) {
        	throw new RuntimeException("Maximum section limit reached");
        }
        
        section.setWebsite(website);
        Optional<TemplateSection> optionalTemplateSection = templateSectionRepository.findById(section.getTemplatesection().getId());
        TemplateSection templateSection = optionalTemplateSection.orElseThrow(() -> new RuntimeException("Template Section not found"));
        section.setTemplatesection(templateSection);
        sectionRepository.save(section);
        List<Section> websections = website.getSections();
        websections.add(section);
        website.setSections(websections);
        return websiteRepository.save(website);
       
	}
	
	@Transactional
	public Website updateName(long websiteID, String Name){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }

        // Get the authenticated user's username and retrieve the user from the database
        String username = authentication.getName();
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!user.getSubscription().getStatus().equals("ACTIVE")) {
        	throw new RuntimeException("Subscription not active ");
        }
        
        
	    Website website = websiteRepository.findById(websiteID).orElseThrow(() -> new RuntimeException("Website not found"));

        if (!website.getUser().getUsername().equals(username)) {
            throw new RuntimeException("User is not authorized to add Section to this website");
        }
        
        website.setName(Name);
        return websiteRepository.save(website);
		
		
	}

}
