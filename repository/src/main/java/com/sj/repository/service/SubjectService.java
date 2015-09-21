package com.sj.repository.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Subject;
import com.sj.model.model.SubjectCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.SubjectJson;

public interface SubjectService {
	Page<Subject> findAll(Pageable pageable);

	Page<Subject> findByActivated(Pageable pageable, ActivateEnum activate);

	Subject findOne(Long id);

	Subject save(Subject s);

	Subject update(Subject s, Subject old);

	Subject update(Subject subject);

	List<Subject> findByShowOnIndex();

	public Page<SubjectJson> findByCategoryAndActivateJson(
			ActivateEnum activate, SubjectCategory category, Pageable pageable);

	Subject updateState(Long id, ActivateEnum active);
}
