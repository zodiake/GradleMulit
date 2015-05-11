package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Advertisement;
import com.sj.model.type.AdvertiseCategoryEnum;

public interface AdvertisementRepository extends
		PagingAndSortingRepository<Advertisement, Long> {
	List<Advertisement> findByCategory(AdvertiseCategoryEnum category,
			Pageable pageable);
	
	Advertisement findByIdAndCategory(Long id,AdvertiseCategoryEnum category);
}
