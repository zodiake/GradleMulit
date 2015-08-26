package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.InformationCategory;

public interface InformationCategoryService {

	public Page<InformationCategory> findAll(Pageable pageable);

	public InformationCategory findOne(Long id);

	public InformationCategory save(InformationCategory category);

	public InformationCategory update(InformationCategory category);
	
	public void delete(Long id);
	
	public InformationCategory findByName(String name);
}
