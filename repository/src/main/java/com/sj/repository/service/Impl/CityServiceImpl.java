package com.sj.repository.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sj.model.model.City;
import com.sj.repository.repository.CityRepository;
import com.sj.repository.service.CityService;

public class CityServiceImpl implements CityService {
	@Autowired
	private CityRepository cityRepository;

	@Override
	public City findOne(Long id) {
		return cityRepository.findOne(id);
	}

}
