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
		template.opsForHash().put(cartlineId, "image", cartline.getImage());
		template.opsForHash().put(cartlineId, "place",
				cartline.getPlace().toString());
		template.opsForHash().put(cartlineId, "brandName",
				cartline.getBrandName());
		template.opsForHash().put(cartlineId, "model", cartline.getModel());
		template.opsForHash().put(cartlineId, "productId",
				cartline.getProductId() + "");
		template.opsForHash().put(cartlineId, "check", String.valueOf(cartline.getCheck()));
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
					String image = (String) template.opsForHash().get(
							redisCartlineId + i, "image");
					String place = (String) template.opsForHash().get(
							redisCartlineId + i, "place");
					String brandName = (String) template.opsForHash().get(
							redisCartlineId + i, "brandName");
					String model = (String) template.opsForHash().get(
							redisCartlineId + i, "model");
					String productId = (String) template.opsForHash().get(
							redisCartlineId + i, "productId");
					String check = (String) template.opsForHash().get(
							redisCartlineId + i, "check");
					return new CartLine(tempId, url, name, price, number,
							image, brandName, model, place, productId, check);
				}).collect(Collectors.toSet());
	}

	@Override
	public void updateNumber(Long id, Long cartlineId, int number) {
		String redisCartlineId = CARTLINE + id + ":" + cartlineId;
		template.opsForHash().put(redisCartlineId, "number", number + "");
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

	@Override
	public void updateCheck(Long id, Long cartlineId, String check) {
		String redisCartlineId = CARTLINE + id + ":" + cartlineId;
		template.opsForHash().put(redisCartlineId, "check", check);
	}
}