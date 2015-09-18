package com.sj.repository.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Brand;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.BrandJson;
import com.sj.repository.search.model.BrandSearchOption;

public interface BrandService {
	public Page<Brand> findAll(Pageable pageable);

	public List<Brand> findAll();

	public Brand findOne(Long id);

	public List<Brand> findByAcitvate(ActivateEnum activate, Pageable pageable);

	public List<Brand> findByAcitvate(ActivateEnum activate);

	public Page<BrandJson> findByActivateToJson(ActivateEnum activate,
			Pageable pageable);

	public Brand save(Brand brand);

	public void update(Brand brand);

	public Brand findByName(String name);

	public void deleteOne(Long id);

	public void activate(Long id, ActivateEnum activate);
	
	public List<Brand> findAllOrderByName();

	public Page<Brand> searchBrand(BrandSearchOption option,Pageable pageable);

	Map<String, String> buildMap(BrandSearchOption option);
}
