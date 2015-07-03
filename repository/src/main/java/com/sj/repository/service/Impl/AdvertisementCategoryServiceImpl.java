package com.sj.repository.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.AdvertisementCategory;
import com.sj.repository.repository.AdvertisementCategoryRepository;
import com.sj.repository.service.AdvertisementCategoryService;

@Service
public class AdvertisementCategoryServiceImpl implements
		AdvertisementCategoryService {
	@Autowired
	private AdvertisementCategoryRepository repository;

	@Override
	public Page<AdvertisementCategory> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public AdvertisementCategory findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public AdvertisementCategory save(AdvertisementCategory category) {
		return repository.save(category);
	}

	@Override
	public AdvertisementCategory update(AdvertisementCategory category) {
		return repository.save(category);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

}
