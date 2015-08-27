package com.sj.repository.service;

import java.util.List;

import com.sj.model.model.AdvertismentCategory;
import com.sj.repository.model.AdvertisementCategoryJson;

public interface AdvertisementCategoryService {
	List<AdvertismentCategory> findAll();

	List<AdvertisementCategoryJson> findAllJson();
}
