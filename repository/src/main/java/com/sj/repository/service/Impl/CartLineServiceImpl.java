package com.sj.repository.service.Impl;

import static com.sj.repository.util.RedisConstant.CART;
import static com.sj.repository.util.RedisConstant.CARTLINE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
		template.opsForList().leftPush(cartId, cartline.getId().toString());

		// add to
		template.opsForHash().put(cartlineId, "id", cartline.getId().toString());
		template.opsForHash().put(cartlineId, "name", cartline.getName());
		template.opsForHash().put(cartlineId, "price", cartline.getPrice() + "");
		template.opsForHash().put(cartlineId, "number",cartline.getNumber() + "");
		template.opsForHash().put(cartlineId, "image", cartline.getImage());
		template.opsForHash().put(cartlineId, "place",cartline.getPlace().toString());
		template.opsForHash().put(cartlineId, "brandName",cartline.getBrandName());
		template.opsForHash().put(cartlineId, "model", cartline.getModel());
		template.opsForHash().put(cartlineId, "productId",cartline.getProductId() + "");
		template.opsForHash().put(cartlineId, "check", String.valueOf(cartline.getCheck()));
	}

	@Override
	public void remove(Long id, Long cartLineId) {
		String cartId = CART + id;
		String cartlineId = CARTLINE + id + ":" + cartLineId;
		template.opsForList().remove(cartId, -1, cartLineId.toString());
		template.delete(cartlineId);
	}

	@Override
	public List<CartLine> findByUser(Long id) {
		List<String> ids = template.opsForList().range(CART+id, 0, -1);
		if(ids==null|| ids.size()==0)
			return null;
		String redisCartlineId = CARTLINE + id + ":";
		return ids
				.stream()
				.map(i -> {
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
					String name = (String) template.opsForHash().get(
							redisCartlineId + i, "name");
					String check = (String) template.opsForHash().get(
							redisCartlineId + i, "check");
					return new CartLine(tempId, name, price, number,image, brandName, model, place, productId, check);
				}).collect(Collectors.toList());
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

	@Override
	public List<CartLine> findByUserAndCheck(Long id) {
		List<String> ids = template.opsForList().range(CART+id, 0, -1);
		String redisCartlineId = CARTLINE + id + ":";
		List<CartLine> lines = new ArrayList<CartLine>();
		for (String str : ids) {
			String check = (String) template.opsForHash().get(redisCartlineId + str, "check");
			if(Boolean.valueOf(check)){
				String tempId = (String) template.opsForHash().get(redisCartlineId + str, "id");
				String price = (String) template.opsForHash().get(redisCartlineId + str, "price");
				String number = (String) template.opsForHash().get(redisCartlineId + str, "number");
				String image = (String) template.opsForHash().get(redisCartlineId + str, "image");
				String place = (String) template.opsForHash().get(redisCartlineId + str, "place");
				String brandName = (String) template.opsForHash().get(redisCartlineId + str, "brandName");
				String model = (String) template.opsForHash().get(redisCartlineId + str, "model");
				String productId = (String) template.opsForHash().get(redisCartlineId + str, "productId");
				String name = (String) template.opsForHash().get(redisCartlineId + str, "name");
				CartLine line = new CartLine(tempId, name, price, number, image, brandName, model, place, productId, check);	
				lines.add(line);
			}
		}
		return lines;
	}
}