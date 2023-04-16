package com.Projet.usermanagement.mapper;

import org.mapstruct.Mapper;

import com.Projet.base.mapper.BaseMapper;
import com.Projet.usermanagement.dto.RegistrationDto;
import com.Projet.usermanagement.entity.AppUser;

@Mapper
public interface RegistrationMapper  extends BaseMapper <AppUser, RegistrationDto>{

}
