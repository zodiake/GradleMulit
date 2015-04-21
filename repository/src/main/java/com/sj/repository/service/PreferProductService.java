package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.PreferProduct;
import com.sj.model.model.Product;
import com.sj.model.model.SiteUser;

public interface PreferProductService {
	public Page<PreferProduct> findByUser(SiteUser user, Pageable pageable);

	public PreferProduct save(PreferProduct product);
	
	//判断该商品是否已经被该用户收藏过
	public boolean isDuplicateProduct(SiteUser user,Product product);
}
