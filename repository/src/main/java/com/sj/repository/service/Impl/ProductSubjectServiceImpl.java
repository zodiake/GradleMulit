package com.sj.repository.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.ProductSubject;
import com.sj.model.model.Subject;
import com.sj.repository.repository.ProductSubjectRepository;
import com.sj.repository.service.ProductSubjectService;

@Service
public class ProductSubjectServiceImpl implements ProductSubjectService {
	@Autowired
	private ProductSubjectRepository repository;

	@Override
	public Page<ProductSubject> findBySubject(Subject subject, Pageable pageable) {
		Page<ProductSubject> results = repository.findBySubject(subject,
				pageable);
		return results;
	}
}
