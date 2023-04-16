package com.Projet.websitemanagement.mapper;

import org.mapstruct.Mapper;

import com.Projet.base.mapper.BaseMapper;
import com.Projet.websitemanagement.dto.TemplateSectionDto;
import com.Projet.websitemanagement.entity.TemplateSection;

@Mapper
public interface TemplateSectionMapper extends BaseMapper <TemplateSection, TemplateSectionDto>{

}
