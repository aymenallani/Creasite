package com.Projet.websitemanagement.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Projet.websitemanagement.service.ManageImagesService;
import com.Projet.websitemanagement.service.SectionService;

import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/ManageImages")
public class ManageImagesController {
	
	@Autowired
	private ManageImagesService manageImagesService;
	
	@PostMapping("")
	public ResponseEntity<?> addContent(MultipartFile imageFile){
		 try {
		return ResponseEntity.ok(manageImagesService.addImage(imageFile));
		 } catch (IOException e) {
		        throw new RuntimeException("Failed to save file", e);
		    }
	}
	
	@GetMapping("/uploads/images/{filename:.+}")
	public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
	    Path path = Paths.get("uploads/images", filename);
	    byte[] imageBytes = Files.readAllBytes(path);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);
	    headers.setContentLength(imageBytes.length);

	    return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
	}


}
