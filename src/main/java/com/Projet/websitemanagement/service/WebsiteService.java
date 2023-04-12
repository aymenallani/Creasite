package com.Projet.websitemanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Projet.base.service.BaseService;
import com.Projet.usermanagement.entity.AppUser;
import com.Projet.usermanagement.repository.UserRepository;
import com.Projet.websitemanagement.entity.Template;
import com.Projet.websitemanagement.entity.Website;
import com.Projet.websitemanagement.repository.TemplateRepository;
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

}
