package com.sj.repository.service.Impl;

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
	public PreferProduct save(PreferProduct product) {
		product.setCreatedTime(Calendar.getInstance());

		// 获取数量
		String count = (String) template.opsForHash().get(
				"prefer:" + product.getProduct().getId(), "count");
		template.opsForSet().add("prefer:",
				product.getProduct().getId().toString());
		// 保存
		if (count == null) {
			template.opsForHash().put(
					"prefer:" + product.getProduct().getId().toString(),"count", "0");
		}else{
			template.opsForHash().put(
					"prefer:" + product.getProduct().getId().toString(),"count", (Long.valueOf(count) + 1)+"");
		}
		return repository.save(product);
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
		// 获取数量
		String count = (String) template.opsForHash().get(
				"prefer:" + product.getId(), "count");
		template.opsForSet().add("prefer:",
				product.getId().toString());
		// 保存
		if (count == null) {
			template.opsForHash().put(
					"prefer:" + product.getId().toString(),"count", "0");
		}else{
			template.opsForHash().put(
					"prefer:" + product.getId().toString(),"count", (Long.valueOf(count) - 1)+"");
		}
		repository.deleteByUserAndProduct(user, product);
	}

	@Override
	public List<PreferProduct> findByUser(SiteUser user) {
		return repository.findByUser(user);
	}
}
