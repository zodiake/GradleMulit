package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Advertisement;
import com.sj.model.model.AdvertisementCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.AdvertisementJson;

public interface AdvertisementService {

	public Page<AdvertisementJson> findAllJson(Pageable pageable,
			ActivateEnum activate);

	public Page<Advertisement> findAll(Pageable pageable);

	public Page<Advertisement> findByActivate(Pageable pageable,
			ActivateEnum activate);

	public Advertisement findOne(Long id);

	public Advertisement update(Advertisement advertisement, Advertisement adv);

	public Advertisement updateStatus(Advertisement adv);

	public Advertisement save(Advertisement advertisement);

	public boolean findByActivate(AdvertisementCategory category);
}
