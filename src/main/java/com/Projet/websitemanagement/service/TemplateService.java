package com.Projet.websitemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Projet.base.service.BaseService;
import com.Projet.websitemanagement.entity.Template;
import com.Projet.websitemanagement.repository.TemplateRepository;

@Service
public class TemplateService extends BaseService<Template, Long>{
	@Autowired
	private TemplateRepository TemplateRepository;
	
	public List<Template> findAll() {
		return TemplateRepository.findAll();
	}
	
	public Template findById(Long id) {
		return super.findById(id);
	}
	

}
