package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.CommonUser;
import com.sj.model.model.PreferProduct;
import com.sj.model.model.Product;

public interface PreferProductService {
	public Page<PreferProduct> findByUser(CommonUser user, Pageable pageable);

	public PreferProduct save(PreferProduct product);

	// 判断该商品是否已经被该用户收藏过
	public boolean isDuplicateProduct(CommonUser user, Product product);

	public void deleteByUserAndProduct(CommonUser user, Product product);
}
