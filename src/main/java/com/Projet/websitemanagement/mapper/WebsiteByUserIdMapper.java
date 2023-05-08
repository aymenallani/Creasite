package com.Projet.websitemanagement.mapper;

import org.mapstruct.Mapper;

import com.Projet.base.mapper.BaseMapper;
import com.Projet.websitemanagement.dto.WebsiteByUserIdDto;
import com.Projet.websitemanagement.entity.Website;

@Mapper
public interface WebsiteByUserIdMapper extends BaseMapper <Website, WebsiteByUserIdDto>{

}
