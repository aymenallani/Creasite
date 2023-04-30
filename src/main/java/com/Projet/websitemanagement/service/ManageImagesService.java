package com.Projet.websitemanagement.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Projet.websitemanagement.entity.Website;

import jakarta.transaction.Transactional;

@Service
public class ManageImagesService {
	
	@Transactional
	public String addImage (MultipartFile imageFile) throws IOException{
		String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        String fileExtension = FilenameUtils.getExtension(fileName);
        String newFileName = UUID.randomUUID().toString() + "." + fileExtension;
        Path path = Paths.get("uploads/images/" + newFileName);
        Files.createDirectories(path.getParent());
        Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        String imageUrl = "/uploads/images/" + newFileName;
        return imageUrl;
		
	}

}
