package com.Projet.contactUs.emailRequestClass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {
    private String toEmail;
    private String body;
    private String subject;
}
