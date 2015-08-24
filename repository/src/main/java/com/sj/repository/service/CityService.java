package com.sj.repository.service;

import java.util.Set;

import com.sj.model.model.City;
import com.sj.model.model.Province;

public interface CityService {
	public City findOne(Long id);
	public Set<City> findByProvince(Province province);
}
