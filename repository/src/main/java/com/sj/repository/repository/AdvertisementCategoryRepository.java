package com.sj.repository.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.AdvertisementCategory;

public interface AdvertisementCategoryRepository extends
		PagingAndSortingRepository<AdvertisementCategory, Long> {

}
