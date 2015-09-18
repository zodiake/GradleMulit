package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sj.model.model.SiteRole;
import com.sj.model.type.ActivateEnum;

public interface SiteRoleRepository extends CrudRepository<SiteRole, Long> {
	public List<SiteRole> findByActive(ActivateEnum active);
}
