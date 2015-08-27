package com.sj.repository.repository;

import org.springframework.data.repository.CrudRepository;

import com.sj.model.model.AdvertisementCategory;

public interface AdvertisementCategoryRepostiory extends
		CrudRepository<AdvertisementCategory, Long> {

}
