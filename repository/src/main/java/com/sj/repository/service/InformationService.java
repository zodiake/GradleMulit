package com.sj.repository.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Information;
import com.sj.model.model.InformationCategory;
import com.sj.model.type.AdvertiseCategoryEnum;

public interface InformationService {
	public Page<Information> findByCategory(InformationCategory category,
			Pageable pageable);

	public Information update(Information info);

	public Information findByIdAndCategory(Long id,
			AdvertiseCategoryEnum category);
	
	public Information save(Information advertisement);
	
	public Information create(Information info);
	
	public Information findOne(Long id);
	
	public List<Information> findByCategoryAndShowOnIndex(InformationCategory category);
	
	public Page<Information> findAll(Pageable pageable);
}