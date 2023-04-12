package com.Projet.websitemanagement.repository;

import org.springframework.stereotype.Repository;

import com.Projet.base.repository.BaseRepository;
import com.Projet.websitemanagement.entity.Website;


@Repository
public interface WebsiteRepository extends BaseRepository <Website, Long>{

}
