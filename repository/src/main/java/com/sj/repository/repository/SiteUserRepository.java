package com.sj.repository.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;

public interface SiteUserRepository extends PagingAndSortingRepository<SiteUser, Integer>{
	public SiteUser findByNameAndEnabled(String name,ActivateEnum activate);
}
