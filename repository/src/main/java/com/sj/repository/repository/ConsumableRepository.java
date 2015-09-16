package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Consumable;
import com.sj.model.model.ProductCategory;
import com.sj.model.type.ProductStatusEnum;

public interface ConsumableRepository extends
		PagingAndSortingRepository<Consumable, Long> {
	public Page<Consumable> findByFirstCategoryAndSecondCategoryAndStatus(
			Pageable pageable, ProductCategory fc, ProductCategory sc,
			ProductStatusEnum status);
}
