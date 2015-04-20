package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.PreferProduct;
import com.sj.model.model.SiteUser;

public interface PreferProductService {
	public Page<PreferProduct> findByUser(SiteUser user,Pageable pageable);
}
