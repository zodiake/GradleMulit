package com.sj.repository.service.Impl;

import static com.sj.repository.util.RedisConstant.COLLECTIONCOUNT;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.sj.model.model.CommonUser;
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
	@Autowired
	private StringRedisTemplate template;

	@Override
	public Page<PreferProduct> findByUser(SiteUser user, Pageable pageable) {
		CommonUser jpaUser = new CommonUser(user.getId());
		Page<PreferProduct> preferProducts = repository.findByUser(jpaUser,
				pageable);
		preferProducts.forEach(p -> p.getProduct());
		return preferProducts;
	}

	@Override
	public PreferProduct save(PreferProduct preferProduct) {
		preferProduct.setCreatedTime(Calendar.getInstance());
		preferProduct = repository.save(preferProduct);
		template.opsForValue().increment(COLLECTIONCOUNT + preferProduct.getProduct().getId(), 1);
		return preferProduct;
	}

	@Override
	public boolean isDuplicateProduct(CommonUser user, Product product) {
		CommonUser temp = new CommonUser(user.getId());
		Long l = repository.countByUserAndProduct(temp, product);
		if (l == 0l)
			return false;
		else
			return true;
	}

	@Override
	public void deleteByUserAndProduct(CommonUser user, Product product) {
		template.opsForValue().increment(COLLECTIONCOUNT + product.getId(), -1);
		repository.deleteByUserAndProduct(user, product);
	}

	@Override
	public List<PreferProduct> findByUser(SiteUser user) {
		return repository.findByUser(user);
	}
}
