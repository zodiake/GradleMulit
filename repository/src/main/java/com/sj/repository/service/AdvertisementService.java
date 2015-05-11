package com.sj.repository.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sj.model.model.Advertisement;
import com.sj.model.type.AdvertiseCategoryEnum;

public interface AdvertisementService {
	public List<Advertisement> findByCategory(AdvertiseCategoryEnum cateogry,
			Pageable pageable);

	public Advertisement update(Advertisement advertisement);

	public Advertisement findByIdAndCategory(Long id,
			AdvertiseCategoryEnum category);
}
