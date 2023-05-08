package com.Projet.websitemanagement.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.Projet.base.repository.BaseRepository;
import com.Projet.websitemanagement.entity.TemplateSection;
import com.Projet.websitemanagement.entity.Website;


@Repository
public interface WebsiteRepository extends BaseRepository <Website, Long>{
	List<Website> findByUserId(Long id);
}
