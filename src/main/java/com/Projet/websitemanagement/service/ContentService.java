package com.Projet.websitemanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Projet.base.service.BaseService;
import com.Projet.usermanagement.entity.AppUser;
import com.Projet.usermanagement.repository.UserRepository;
import com.Projet.websitemanagement.entity.Content;
import com.Projet.websitemanagement.entity.Section;
import com.Projet.websitemanagement.repository.ContentRepository;

import jakarta.transaction.Transactional;

@Service
public class ContentService extends BaseService<Content, Long>{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ContentRepository contentRepository;
	
	@Transactional
	public void deleteContentById(Long contentId) {
		Content content = contentRepository.findById(contentId).orElseThrow(() -> new RuntimeException("Content not found"));
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }
        String username = authentication.getName();
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        if (!content.getSection().getWebsite().getUser().getUsername().equals(username)) {
            throw new RuntimeException("User is not authorized to delete this content");
        }
        contentRepository.delete(content);
	}
	
	public Content findById(Long id) {
		Optional<Content> optionalContent = contentRepository.findById(id);
		Content content = optionalContent.orElseThrow(() -> new RuntimeException("Content not found"));
		return content;
	}
}
