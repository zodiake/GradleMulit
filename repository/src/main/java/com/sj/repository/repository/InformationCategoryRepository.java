package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.InformationCategory;
import com.sj.model.type.ActivateEnum;

public interface InformationCategoryRepository extends
		PagingAndSortingRepository<InformationCategory, Long> {
	
	InformationCategory findByNameAndActivate(String name,ActivateEnum activate);
	
	public List<InformationCategory> findByParent(InformationCategory ic);
	
	public InformationCategory findByParentIsNull();
}
