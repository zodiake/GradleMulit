package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Advertisement;
import com.sj.model.model.AdvertisementCategory;
import com.sj.model.type.ActivateEnum;

public interface AdvertisementRepository extends
		PagingAndSortingRepository<Advertisement, Long> {
	Page<Advertisement> findByActivate(Pageable pageable,ActivateEnum activate);
	
	List<Advertisement> findByActivateAndCategory(ActivateEnum activate,AdvertisementCategory category);
}
