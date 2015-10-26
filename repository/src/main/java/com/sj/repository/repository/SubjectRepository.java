package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Subject;
import com.sj.model.model.SubjectCategory;
import com.sj.model.type.ActivateEnum;

public interface SubjectRepository extends
		PagingAndSortingRepository<Subject, Long> {
	Page<Subject> findByCategoryAndActivateOrderByCreatedTimeDesc(
			SubjectCategory category, Pageable pageable, ActivateEnum activate);

	Page<Subject> findByActivateOrderByCreatedTimeDesc(Pageable pageable, ActivateEnum activate);
	
	List<Subject> findByShowOnIndexAndActivateAndCategory(ActivateEnum activate,
			ActivateEnum acti,SubjectCategory category);

}