package com.sj.repository.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sj.repository.model.SiteRoleJson;

public interface SiteRoleService {
	public List<SiteRoleJson> findAll(Pageable pageable);
}
