package com.Projet.contactUs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Projet.contactUs.emailRequestClass.EmailRequest;
import com.Projet.contactUs.service.EmailSenderService;





@RestController
public class EmailController {
	@Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/contact")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {
    	try {
        emailSenderService.sendEmail(emailRequest);
        return ResponseEntity.ok("Email sent successfully!");
    	 } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                  .body("Failed to send email: " + e.getMessage());
         }

    }

}