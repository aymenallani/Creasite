package com.Projet.websitemanagement.mapper;
import com.Projet.base.mapper.BaseMapper;
import com.Projet.websitemanagement.dto.SectionDto;
import com.Projet.websitemanagement.entity.Section;

import org.mapstruct.Mapper;

@Mapper
public interface SectionMapper extends BaseMapper <Section, SectionDto>{

}
