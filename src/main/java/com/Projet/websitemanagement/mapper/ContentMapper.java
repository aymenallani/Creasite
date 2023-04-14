package com.Projet.websitemanagement.mapper;

import org.mapstruct.Mapper;

import com.Projet.base.mapper.BaseMapper;
import com.Projet.websitemanagement.dto.ContentDto;
import com.Projet.websitemanagement.entity.Content;

@Mapper
public interface ContentMapper extends BaseMapper <Content, ContentDto>{

}
