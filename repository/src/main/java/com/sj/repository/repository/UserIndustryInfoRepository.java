package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.UserIndustryInfo;

public interface UserIndustryInfoRepository extends
		PagingAndSortingRepository<UserIndustryInfo, Integer> {
	List<UserIndustryInfo> findAll();
}
