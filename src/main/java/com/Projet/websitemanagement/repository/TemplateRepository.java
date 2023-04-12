package com.Projet.websitemanagement.repository;

import org.springframework.stereotype.Repository;

import com.Projet.base.repository.BaseRepository;
import com.Projet.websitemanagement.entity.Template;

@Repository
public interface TemplateRepository extends BaseRepository <Template, Long>{

}
