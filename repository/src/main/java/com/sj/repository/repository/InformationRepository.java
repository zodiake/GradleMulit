package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Information;
import com.sj.model.model.InformationCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.model.type.AdvertiseCategoryEnum;

public interface InformationRepository extends
		PagingAndSortingRepository<Information, Long> {

	Page<Information> findByCategory(InformationCategory category,
			Pageable pageable);

	Information findByIdAndCategory(Long id, AdvertiseCategoryEnum category);

	Page<Information> findByActivateOrderByUpdatedTimeDesc(Pageable pageable,
			ActivateEnum activate);

	Page<Information> findByOrderByUpdatedTimeDesc(Pageable pageable);
}
