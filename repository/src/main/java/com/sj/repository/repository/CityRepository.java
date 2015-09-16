package com.sj.repository.repository;

import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.City;
import com.sj.model.model.Province;

public interface CityRepository extends
		PagingAndSortingRepository<City, Long> {
	Set<City> findByProvince(Province province);
}
