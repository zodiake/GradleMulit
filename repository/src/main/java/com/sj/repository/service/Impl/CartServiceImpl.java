package com.sj.repository.service.Impl;

import static com.sj.repository.util.RedisConstant.CART;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sj.model.model.Product;
import com.sj.repository.service.CartService;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CartServiceImpl implements CartService {
	@Autowired
	private StringRedisTemplate template;

	@Override
	public boolean contains(Long id, Product product) {
		Long rank = template.opsForZSet().rank(CART + id, product.getId()+"");
		if (rank != null)
			return true;
		return false;
	}
}