package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Advertisement;
import com.sj.model.model.AdvertismentCategory;
import com.sj.model.type.ActivateEnum;

public interface AdvertisementService {
	public Page<Advertisement> findAll(Pageable pageable);
	
	public Page<Advertisement> findByActivate(Pageable pageable,ActivateEnum activate);
	
	public Advertisement findOne(Long id);
	
	public Advertisement update(Advertisement advertisement,Advertisement adv);
	
	public Advertisement updateStatus(Advertisement adv);
	
	public Advertisement save(Advertisement advertisement);
	
	public boolean findByActivate(AdvertismentCategory category);
}
