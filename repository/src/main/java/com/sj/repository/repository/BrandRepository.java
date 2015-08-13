package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Brand;
import com.sj.model.type.ActivateEnum;

public interface BrandRepository extends
		PagingAndSortingRepository<Brand, Long> {
	public List<Brand> findByActivate(ActivateEnum activate, Pageable pageable);

	public List<Brand> findByActivate(ActivateEnum activate);
	
	public Brand findByName(String name);

}
