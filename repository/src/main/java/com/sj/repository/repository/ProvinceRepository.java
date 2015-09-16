package com.sj.repository.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Province;

public interface ProvinceRepository extends
		PagingAndSortingRepository<Province, Long> {

}
