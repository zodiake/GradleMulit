package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sj.model.model.UserIndustryInfo;

public interface UserIndustryInfoRepository extends CrudRepository<UserIndustryInfo, Integer> {
	List<UserIndustryInfo> findAll();
}
