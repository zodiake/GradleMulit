package com.sj.repository.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Manufacturer;

public interface ManufacturerRepository extends
		PagingAndSortingRepository<Manufacturer, Integer> {

}
