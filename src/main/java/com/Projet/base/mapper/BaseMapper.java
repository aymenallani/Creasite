package com.Projet.base.mapper;

import java.util.List;

public interface BaseMapper <T,DTO> {
	DTO map (T t);
	T unMap (DTO dto);
	List<DTO> map (List<T> t);
	List<T> unMap (List<DTO> dto);


}