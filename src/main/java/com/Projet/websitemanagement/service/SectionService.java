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
import com.Projet.websitemanagement.entity.Content;
import com.Projet.websitemanagement.entity.EditableRegion;
import com.Projet.websitemanagement.entity.Section;
import com.Projet.websitemanagement.entity.Website;
import com.Projet.websitemanagement.repository.ContentRepository;
import com.Projet.websitemanagement.repository.EditableRegionRepository;
import com.Projet.websitemanagement.repository.SectionRepository;

import jakarta.transaction.Transactional;

@Service
public class SectionService extends BaseService<Section, Long> {
	
	@Autowired
	private SectionRepository sectionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EditableRegionRepository editableRegionRepository;
	@Autowired
	private ContentRepository contentRepository;
	
	@Transactional
	public void deleteSectionById(Long sectionId) {
		Section section = sectionRepository.findById(sectionId).orElseThrow(() -> new RuntimeException("Section not found"));
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }
        String username = authentication.getName();
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        if (!section.getWebsite().getUser().getUsername().equals(username)) {
            throw new RuntimeException("User is not authorized to delete this section");
        }
        sectionRepository.delete(section);
	}
	
	@Transactional
	public Section addContent (long sectionID, Content content) {
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
        
        
	    Section section = sectionRepository.findById(sectionID).orElseThrow(() -> new RuntimeException("Section not found"));

	    if (!section.getWebsite().getUser().getUsername().equals(username)) {
            throw new RuntimeException("User is not authorized to update this section");
        }
        
        
	    Content newContent = new Content();
	    newContent.setContent(content.getContent());
	    newContent.setSection(section);
	    Optional<EditableRegion> optionalEditableRegion = editableRegionRepository.findById(content.getEditableRegion().getId());
	    EditableRegion editableRegion = optionalEditableRegion.orElseThrow(() -> new RuntimeException("Editable Region not found"));
	    newContent.setEditableRegion(editableRegion);
	    contentRepository.save(newContent);
	    List<Content> sectionContents = section.getContents();
	    sectionContents.add(newContent);
	    section.setContents(sectionContents);
	    return sectionRepository.save(section);
	}
	
	public Section findById(Long id) {
		Optional<Section> optionalSection = sectionRepository.findById(id);
		Section section = optionalSection.orElseThrow(() -> new RuntimeException("Section not found"));
		return section;
	}

}
