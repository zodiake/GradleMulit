package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.AdvertisementCategory;

public interface AdvertisementCategoryService {

	public Page<AdvertisementCategory> findAll(Pageable pageable);

	public AdvertisementCategory findOne(Long id);

	public AdvertisementCategory save(AdvertisementCategory category);

	public AdvertisementCategory update(AdvertisementCategory category);
}
