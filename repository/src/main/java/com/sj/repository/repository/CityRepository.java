package com.sj.repository.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.City;

public interface CityRepository extends
		PagingAndSortingRepository<City, Long> {

}
