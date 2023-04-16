package com.Projet.websitemanagement.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import com.Projet.error.DataNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Optional;
import java.util.UUID;

import org.springframework.util.StringUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		Section section = sectionRepository.findById(sectionId).orElseThrow(() -> new DataNotFoundException("Section not found"));
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("User not authenticated");
        }
        String username = authentication.getName();
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!section.getWebsite().getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("User is not authorized to delete this section");
        }
        sectionRepository.delete(section);
	}
	
	@Transactional
	public Section addContent (long sectionID, Content content, MultipartFile imageFile) throws IOException{
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
        
        
	    Section section = sectionRepository.findById(sectionID).orElseThrow(() -> new DataNotFoundException("Section not found"));

	    if (!section.getWebsite().getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("User is not authorized to update this section");
        }
        
	    Optional<EditableRegion> optionalEditableRegion = editableRegionRepository.findById(content.getEditableRegion().getId());
	    EditableRegion editableRegion = optionalEditableRegion.orElseThrow(() -> new DataNotFoundException("Editable Region not found"));
	    Content newContent = new Content();
	    if (editableRegion.getType().equals("text")) {
	        newContent.setContent(content.getContent());
	    } else if (editableRegion.getType().equals("image")) {
	        // Save the image here
	        String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
	        String fileExtension = FilenameUtils.getExtension(fileName);
	        String newFileName = UUID.randomUUID().toString() + "." + fileExtension;
	        Path path = Paths.get("uploads/images/" + newFileName);
	        Files.createDirectories(path.getParent());
	        Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	        String imageUrl = "/uploads/images/" + newFileName;
	        newContent.setContent(imageUrl);
	    } else {
	        throw new IllegalArgumentException("Unsupported editableRegion type: " + editableRegion.getType());
	    }
	    
	    newContent.setSection(section);

	    newContent.setEditableRegion(editableRegion);
	    contentRepository.save(newContent);
	    List<Content> sectionContents = section.getContents();
	    sectionContents.add(newContent);
	    section.setContents(sectionContents);
	    return sectionRepository.save(section);
	}
	
	public Section findById(Long id) {
		Optional<Section> optionalSection = sectionRepository.findById(id);
		Section section = optionalSection.orElseThrow(() -> new DataNotFoundException("Section not found"));
		return section;
	}

}
