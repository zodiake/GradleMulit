package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;

public interface SiteUserRepository extends
		PagingAndSortingRepository<SiteUser, Integer> {
	public SiteUser findByNameAndEnabled(String name, ActivateEnum activate);

	public Page<SiteUser> findAll(Pageable pageable);
	
	@Modifying	
	@Query("update SiteUser u set u.enabled=:enabled where u.id=:id")
	public void updateEnabled(@Param("enabled")ActivateEnum state,@Param("id")int id);
}
