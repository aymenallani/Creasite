package com.Projet.websitemanagement.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.Projet.base.repository.BaseRepository;
import com.Projet.websitemanagement.entity.TemplateSection;

@Repository
public interface TemplateSectionRepository extends BaseRepository <TemplateSection, Long>{
	List<TemplateSection> findByTemplateId(Long id);


}
