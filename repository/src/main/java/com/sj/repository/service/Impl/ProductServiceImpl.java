package com.sj.repository.service.Impl;

import static com.sj.repository.util.RedisConstant.VIEWCOUNT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.sj.model.model.Product;
import com.sj.model.model.Provider;
import com.sj.repository.repository.ProductRepository;
import com.sj.repository.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository repository;

	@Autowired
	private StringRedisTemplate template;

	@Override
	public Page<Product> findByUsers(Provider user, Pageable pageable) {
		Page<Product> pages = repository.findByCreatedBy(user, pageable);
		List<Product> lists = pages.getContent();
		lists.stream().forEach(
				l -> {
					String count = template.opsForValue().get(
							VIEWCOUNT + l.getId().toString());
					if (count != null)
						l.setViewCount(Long.valueOf(count));
					else
						l.setViewCount(0l);
				});
		return pages;
	}

	@Override
	public Product findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void addViewCount(Long id) {
		template.opsForValue().increment(VIEWCOUNT + id, 1);
	}
}
