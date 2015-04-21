package com.sj.repository.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.PreferProduct;
import com.sj.model.model.Product;
import com.sj.model.model.SiteUser;
import com.sj.repository.repository.PreferProductRepository;
import com.sj.repository.service.PreferProductService;

@Service
@Transactional
public class PreferProductServiceImpl implements PreferProductService {
	@Autowired
	private PreferProductRepository repository;

	@Override
	public Page<PreferProduct> findByUser(SiteUser user, Pageable pageable) {
		SiteUser jpaUser = new SiteUser(user.getId());
		Page<PreferProduct> preferProducts = repository.findByUser(jpaUser,
				pageable);
		preferProducts.forEach(p -> p.getProduct());
		return preferProducts;
	}

	@Override
	public PreferProduct save(PreferProduct product) {
		return repository.save(product);
	}

	@Override
	public boolean isDuplicateProduct(SiteUser user, Product product) {
		Long l = repository.countByUserAndProduct(user, product);
		if (l == 0l)
			return false;
		else
			return true;
	}
}