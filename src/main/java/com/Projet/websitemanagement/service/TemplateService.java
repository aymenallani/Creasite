package com.Projet.websitemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Projet.base.service.BaseService;
import com.Projet.error.DataNotFoundException;
import com.Projet.websitemanagement.entity.Template;
import com.Projet.websitemanagement.repository.TemplateRepository;

@Service
public class TemplateService extends BaseService<Template, Long>{
	@Autowired
	private TemplateRepository templateRepository;
	
	public List<Template> findAll() {
		return templateRepository.findAll();
	}
	
	public Template findById(Long id) {
		Optional<Template> optionalTemplate = templateRepository.findById(id);
        Template template = optionalTemplate.orElseThrow(() -> new DataNotFoundException("Template not found"));
        return template;
	}

}
