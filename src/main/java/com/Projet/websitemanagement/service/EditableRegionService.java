package com.Projet.websitemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Projet.base.service.BaseService;
import com.Projet.error.DataNotFoundException;
import com.Projet.websitemanagement.entity.EditableRegion;
import com.Projet.websitemanagement.entity.Section;
import com.Projet.websitemanagement.entity.TemplateSection;
import com.Projet.websitemanagement.repository.EditableRegionRepository;

@Service
public class EditableRegionService extends BaseService<EditableRegion, Long>{
	
	@Autowired
	private EditableRegionRepository editableRegionRepository;
	@Autowired
	private TemplateSectionService templateSectionService;
	
	public EditableRegion findById(Long id) {
		Optional<EditableRegion> optionalEditableRegion = editableRegionRepository.findById(id);
		EditableRegion editableRegion = optionalEditableRegion.orElseThrow(() -> new DataNotFoundException("Editable Region Section not found"));
        return editableRegion;
	}
	
	public List<EditableRegion> findByTemplateSectionId(Long id) {
		TemplateSection templateSection = templateSectionService.findById(id);
		List<EditableRegion> EditableRegions= editableRegionRepository.findByTemplatesectionId(templateSection.getId());
		return EditableRegions;

	}

}
