package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Consumable;
import com.sj.model.model.ProductCategory;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.model.ProductJson;

public interface ConsumableService {
	public Consumable saveNoPublisher(Consumable consumable);

	public Page<ProductJson> findByFirstCategoryAndSecondCategoryAndStatusJson(
			Pageable pageable, ProductCategory fc, ProductCategory sc,
			ProductStatusEnum status);

	public Consumable updateNoPublisher(Consumable consumable);
}
