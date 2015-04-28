package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.ProductSubject;
import com.sj.model.model.Subject;

public interface ProductSubjectService {
	Page<ProductSubject> findBySubject(Subject subject,Pageable pageable);
}
