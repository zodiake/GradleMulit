package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Subject;
import com.sj.model.type.ActivateEnum;

public interface SubjectRepository extends PagingAndSortingRepository<Subject, Integer> {
	Page<Subject> findByActivate(Pageable pageable,ActivateEnum activate);
}
