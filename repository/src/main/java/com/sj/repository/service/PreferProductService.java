package com.sj.repository.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.CommonUser;
import com.sj.model.model.PreferProduct;
import com.sj.model.model.Product;
import com.sj.model.model.SiteUser;

public interface PreferProductService {
	public Page<PreferProduct> findByUser(CommonUser user, Pageable pageable);
	
	public List<PreferProduct> findByUser(CommonUser user);

	public PreferProduct save(PreferProduct product);

	public boolean isDuplicateProduct(CommonUser user, Product product);

	public void deleteByUserAndProduct(CommonUser user, Product product);
}
