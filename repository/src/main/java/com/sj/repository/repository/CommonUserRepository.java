package com.sj.repository.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.CommonUser;

public interface CommonUserRepository extends PagingAndSortingRepository<CommonUser, Long> {
	CommonUser findByName(String name);
}
