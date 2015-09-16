package com.sj.repository.service;

import java.util.List;

import com.sj.model.model.AdvertisementCategory;
import com.sj.repository.model.AdvertisementCategoryJson;

public interface AdvertisementCategoryService {
	List<AdvertisementCategory> findAll();

	List<AdvertisementCategoryJson> findAllJson();
}
