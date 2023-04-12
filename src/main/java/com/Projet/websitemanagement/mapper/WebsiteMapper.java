package com.Projet.websitemanagement.mapper;

import org.mapstruct.Mapper;

import com.Projet.base.mapper.BaseMapper;
import com.Projet.websitemanagement.dto.WebsiteDto;
import com.Projet.websitemanagement.entity.Website;


@Mapper
public interface WebsiteMapper extends BaseMapper <Website, WebsiteDto>{

}
