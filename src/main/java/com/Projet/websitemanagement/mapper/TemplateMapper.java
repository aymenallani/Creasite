package com.Projet.websitemanagement.mapper;

import org.mapstruct.Mapper;

import com.Projet.base.mapper.BaseMapper;
import com.Projet.websitemanagement.dto.TemplateDto;
import com.Projet.websitemanagement.entity.Template;


@Mapper
public interface TemplateMapper extends BaseMapper <Template, TemplateDto>{

}
