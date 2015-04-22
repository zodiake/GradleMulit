package com.sj.repository.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Subject;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.repository.SubjectRepository;
import com.sj.repository.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	private SubjectRepository repository;

	@Override
	public Page<Subject> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Subject> findByActivated(Pageable pageable,ActivateEnum activate) {
		return repository.findByActivate(pageable, activate);
	}

}
