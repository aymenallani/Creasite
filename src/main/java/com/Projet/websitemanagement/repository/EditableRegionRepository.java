package com.Projet.websitemanagement.repository;

import java.util.List;

import com.Projet.base.repository.BaseRepository;
import com.Projet.websitemanagement.entity.EditableRegion;
import com.Projet.websitemanagement.entity.TemplateSection;

public interface EditableRegionRepository extends BaseRepository <EditableRegion, Long>{
	List<EditableRegion> findByTemplatesectionId(Long id);

}
