package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Product;
import com.sj.model.model.ProductSubject;
import com.sj.model.model.Subject;

public interface ProductSubjectRepository extends
		PagingAndSortingRepository<ProductSubject, Long> {
	Page<Product> findBySubject(Subject subject ,Pageable pageable);
}
