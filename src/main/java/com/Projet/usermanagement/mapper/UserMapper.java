package com.Projet.usermanagement.mapper;

import org.mapstruct.Mapper;

import com.Projet.base.mapper.BaseMapper;
import com.Projet.usermanagement.dto.UserDto;
import com.Projet.usermanagement.entity.AppUser;

@Mapper
public interface UserMapper extends BaseMapper <AppUser, UserDto> {

}
