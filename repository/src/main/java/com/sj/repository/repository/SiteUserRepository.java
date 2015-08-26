package com.sj.repository.repository;

import java.util.Calendar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;

public interface SiteUserRepository extends
		PagingAndSortingRepository<SiteUser, Long> {
	public SiteUser findByNameAndEnabled(String name, ActivateEnum activate);

	public SiteUser findByNameOrPhoneOrEmailAndEnabled(String name,String phone,String email,ActivateEnum activate);
	
	public Page<SiteUser> findAll(Pageable pageable);
	
	@Modifying	
	@Query("update SiteUser u set u.enabled=:enabled,u.authenticatedTime=:time where u.id=:id")
	public void updateEnabled(@Param("enabled")ActivateEnum state,@Param("time")Calendar time,@Param("id")Long id);
	
	public SiteUser findByName(String name);
	
	public SiteUser findByPhone(String phone);
	
	public SiteUser findByEmail(String email);
	
	public SiteUser findByNameAndEnabledAndSiteAuthority(String name,ActivateEnum activate,String siteAuthority);
	
}
