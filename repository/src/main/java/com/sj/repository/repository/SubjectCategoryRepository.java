package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.SubjectCategory;
import com.sj.model.type.ActivateEnum;

public interface SubjectCategoryRepository extends
		PagingAndSortingRepository<SubjectCategory, Long> {
	public List<SubjectCategory> findByActivate(ActivateEnum activated);
}
