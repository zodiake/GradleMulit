package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Product;
import com.sj.model.model.Subject;

public interface ProductSubjectService {
	Page<Product> findBySubject(Subject subject,Pageable pageable);
}
