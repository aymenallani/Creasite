package com.Projet.websitemanagement.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Projet.base.service.BaseService;
import com.Projet.usermanagement.entity.AppUser;
import com.Projet.usermanagement.repository.UserRepository;
import com.Projet.websitemanagement.entity.Content;
import com.Projet.websitemanagement.entity.EditableRegion;
import com.Projet.websitemanagement.entity.Section;
import com.Projet.websitemanagement.repository.ContentRepository;
import com.Projet.websitemanagement.repository.EditableRegionRepository;
import com.Projet.error.DataNotFoundException;


import jakarta.transaction.Transactional;

@Service
public class ContentService extends BaseService<Content, Long>{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ContentRepository contentRepository;
	@Autowired
	private EditableRegionRepository editableRegionRepository;
	
	@Transactional
	public void deleteContentById(Long contentId) {
		Content content = contentRepository.findById(contentId).orElseThrow(() -> new DataNotFoundException("Content not found"));
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("User not authenticated");
        }
        String username = authentication.getName();
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!content.getSection().getWebsite().getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("User is not authorized to delete this content");
        }
        contentRepository.delete(content);
	}
	
	public Content findById(Long id) {
		Optional<Content> optionalContent = contentRepository.findById(id);
		Content content = optionalContent.orElseThrow(() -> new DataNotFoundException("Content not found"));
		return content;
	}
	
	public Content updateContent(Long contentId, String Content, MultipartFile imageFile)throws IOException{
		Content content = contentRepository.findById(contentId).orElseThrow(() -> new DataNotFoundException("Content not found"));
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("User not authenticated");
        }
        String username = authentication.getName();
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!content.getSection().getWebsite().getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("User is not authorized to update this content");
        }
        Optional<EditableRegion> optionalEditableRegion = editableRegionRepository.findById(content.getEditableRegion().getId());
	    EditableRegion editableRegion = optionalEditableRegion.orElseThrow(() -> new DataNotFoundException("Editable Region not found"));
	    if (editableRegion.getType().equals("text")) {
	        content.setContent(Content);
	    }else if (editableRegion.getType().equals("image")) {
	        // Save the image here
	        String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
	        String fileExtension = FilenameUtils.getExtension(fileName);
	        String newFileName = UUID.randomUUID().toString() + "." + fileExtension;
	        Path path = Paths.get("uploads/images/" + newFileName);
	        Files.createDirectories(path.getParent());
	        Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	        String imageUrl = "/uploads/images/" + newFileName;
	        content.setContent(imageUrl);
	    } else {
	        throw new IllegalArgumentException("Unsupported editableRegion type: " + editableRegion.getType());
	    }
        return contentRepository.save(content);
	}
}
