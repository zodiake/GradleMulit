package com.sj.repository.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.SubjectCategory;

public interface SubjectCategoryRepository extends
		PagingAndSortingRepository<SubjectCategory, Long> {

}
