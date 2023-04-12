package com.Projet.usermanagement.mapper;

import org.mapstruct.Mapper;

import com.Projet.base.mapper.BaseMapper;
import com.Projet.usermanagement.dto.SubscriptionDto;
import com.Projet.usermanagement.entity.Subscription;

@Mapper
public interface SubscriptionMapper extends BaseMapper <Subscription, SubscriptionDto>{

}
