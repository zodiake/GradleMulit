package com.sj.repository.service.Impl;

import static com.sj.repository.util.RedisConstant.CART;
import static com.sj.repository.util.RedisConstant.CARTLINE;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sj.model.model.CartLine;
import com.sj.repository.repository.ProductRepository;
import com.sj.repository.service.CartLineService;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CartLineServiceImpl implements CartLineService {
	@Autowired
	private StringRedisTemplate template;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void save(Long id, CartLine cartline) {
		String cartlineId = CARTLINE + id + ":" + cartline.getId();
		String cartId = CART + id;

		// add to user cart
		template.opsForSet().add(cartId, cartline.getId().toString());

		// add to
		template.opsForHash()
				.put(cartlineId, "id", cartline.getId().toString());
		template.opsForHash().put(cartlineId, "name", cartline.getName());
		template.opsForHash().put(cartlineId, "url", cartline.getUrl());
		template.opsForHash()
				.put(cartlineId, "price", cartline.getPrice() + "");
		template.opsForHash().put(cartlineId, "number",
				cartline.getNumber() + "");
	}

	@Override
	public void remove(Long id, Long productId) {
		String cartId = CART + id;
		String cartlineId = CARTLINE + id + ":" + productId;
		template.opsForSet().remove(cartId, productId.toString());
		template.delete(cartlineId);
	}

	@Override
	public Set<CartLine> findByUser(Long id) {
		Set<String> ids = template.opsForSet().members(CART + id);
		String redisCartlineId = CARTLINE + id + ":";
		return ids
				.stream()
				.map(i -> {
					String name = (String) template.opsForHash().get(
							redisCartlineId + i, "name");
					String url = (String) template.opsForHash().get(
							redisCartlineId + i, "url");
					String price = (String) template.opsForHash().get(
							redisCartlineId + i, "price");
					String tempId = (String) template.opsForHash().get(
							redisCartlineId + i, "id");
					String number = (String) template.opsForHash().get(
							redisCartlineId + i, "number");
					return new CartLine(tempId, url, name, price, number);
				}).collect(Collectors.toSet());
	}

	@Override
	public void updateNumber(Long id, Long cartlineId, int number) {
		String redisCartlineId = CARTLINE + id + ":" + cartlineId;
		template.opsForHash().put(redisCartlineId, "number", number);
	}

	@Override
	public void clearCartline(Long id, String... lines) {
		Stream<String> stream;
		if (lines == null)
			stream = template.opsForSet().members(CART + id).stream();
		else
			stream = Arrays.asList(lines).stream();
		stream.forEach(i -> {
			template.opsForHash().delete(CARTLINE + id + ":" + i);
		});
	}
}