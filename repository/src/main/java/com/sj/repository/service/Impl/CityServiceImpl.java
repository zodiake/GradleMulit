package com.sj.repository.service.Impl;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sj.model.model.City;
import com.sj.model.model.Province;
import com.sj.repository.repository.CityRepository;
import com.sj.repository.service.CityService;
import com.sj.repository.service.ProvinceService;
@Service
@Transactional
public class CityServiceImpl implements CityService {
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private ProvinceService provinceService;

	@Override
	public City findOne(Long id) {
		return cityRepository.findOne(id);
	}

	@Override
	@Cacheable(value = "cityCache",key="#province.id")
	public Set<City> findByProvince(Province province) {
		return cityRepository.findByProvince(province);
	}
}
