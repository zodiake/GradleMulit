package com.sj.repository.service.Impl;

import java.util.Calendar;
import java.util.List;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Brand;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.repository.BrandRepository;
import com.sj.repository.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandRepository repository;

	@Override
	public Page<Brand> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<Brand> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Brand findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Brand> findByAcitvate(ActivateEnum activate, Pageable pageable) {
		return repository.findByActivate(activate, pageable);
	}

	@Override
	public Brand save(Brand brand) {
		Calendar c = Calendar.getInstance();
		brand.setCraetedTime(c);
		//未图片地址
		
		return repository.save(brand);
	}

	@Override
	public Brand update(Brand brand) {
		return repository.save(brand);
	}

	@Override
	@Cacheable(value = "brandCache")
	public List<Brand> findByAcitvate(ActivateEnum activate) {
		return repository.findByActivate(activate);
	}

	@Override
	public Brand findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public void deleteOne(Long id) {
		repository.delete(id);
	}
}
