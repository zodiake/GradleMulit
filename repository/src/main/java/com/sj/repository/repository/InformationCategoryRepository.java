package com.sj.repository.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.InformationCategory;

public interface InformationCategoryRepository extends
		PagingAndSortingRepository<InformationCategory, Long> {
	InformationCategory findByUrl(String url);
}
