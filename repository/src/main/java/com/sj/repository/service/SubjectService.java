package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Subject;
import com.sj.model.type.ActivateEnum;

public interface SubjectService {
	Page<Subject> findAll(Pageable pageable);

	Page<Subject> findByActivated(Pageable pageable, ActivateEnum activate);

	Subject findOne(Long id);
}
