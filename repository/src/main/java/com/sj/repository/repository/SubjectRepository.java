package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Subject;
import com.sj.model.type.ActivateEnum;

public interface SubjectRepository extends
		PagingAndSortingRepository<Subject, Long> {
	Page<Subject> findByActivateOrderByCreatedTimeDesc(Pageable pageable,
			ActivateEnum activate);

	List<Subject> findByShowOnIndexAndActivate(ActivateEnum activate,
			ActivateEnum acti);
}