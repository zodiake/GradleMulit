package com.sj.repository.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Brand;
import com.sj.model.type.ActivateEnum;

public interface BrandService {
	public Page<Brand> findAll(Pageable pageable);

	public List<Brand> findAll();

	public Brand findOne(Long id);

	public List<Brand> findByAcitvate(ActivateEnum activate, Pageable pageable);

	public List<Brand> findByAcitvate(ActivateEnum activate);

	public Brand save(Brand brand);

	public Brand update(Brand brand);
	
	public Brand findByName(String name);
	
	public void deleteOne(Long id);
	
}
