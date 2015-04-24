package com.sj.repository.service.Impl;

import static com.sj.repository.util.RedisConstant.CART;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sj.model.model.CartLine;
import com.sj.repository.repository.ProductRepository;
import com.sj.repository.service.CartLineService;

@Service
@Transactional
public class CartLineServiceImpl implements CartLineService {
	@Autowired
	private StringRedisTemplate template;
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void save(Long id, Long productId, int number) {
		template.opsForZSet().add(CART + id, productId.toString(), number);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void remove(Long id, Long productId) {
		template.opsForZSet().remove(CART + id, productId);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<CartLine> findByUser(Long id) {
		Set<String> ids = template.opsForZSet().range(CART + id, 0, -1);
		return ids
				.stream()
				.map(i -> {
					CartLine l = new CartLine(productRepository.findOne(Long
							.valueOf(i)));
					Double number = template.opsForZSet().score(CART + id, i);
					l.setNumber(number.intValue());
					return l;
				}).collect(Collectors.toSet());
	}
}