package com.Projet.websitemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Projet.base.service.BaseService;
import com.Projet.error.DataNotFoundException;
import com.Projet.websitemanagement.entity.TemplateSection;
import com.Projet.websitemanagement.entity.Website;
import com.Projet.websitemanagement.repository.TemplateSectionRepository;

@Service
public class TemplateSectionService extends BaseService<TemplateSection, Long>{
	
	@Autowired
	private TemplateSectionRepository templateSectionRepository;
	@Autowired
	private WebsiteService websiteService;
	
	public TemplateSection findById(Long id) {
		Optional<TemplateSection> optionalTemplateSection = templateSectionRepository.findById(id);
		TemplateSection templateSection = optionalTemplateSection.orElseThrow(() -> new DataNotFoundException("Template Section not found"));
        return templateSection;
	}
	
	public List<TemplateSection> findByWebsiteId(Long id) {
		Website website = websiteService.findById(id);
		List<TemplateSection> templateSections= templateSectionRepository.findByTemplateId(website.getTemplate().getId());
		return templateSections;

	}
}
