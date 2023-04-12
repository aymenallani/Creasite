package com.Projet.base.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.Projet.base.repository.BaseRepository;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseService <T,ID> {
	@Autowired
	private BaseRepository<T, ID> baseRepository;
	
	public T findById(ID id) {
		
		Optional<T> entity = baseRepository.findById(id);
		return entity.get();
	}
	
	public T insert(T entity) {
		return baseRepository.save(entity);

	}
	
	public T update(T entity) {
		return baseRepository.save(entity);
	}

	public void deleteById(ID id) {
		baseRepository.deleteById(id);
	}

}