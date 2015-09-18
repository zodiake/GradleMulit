package com.sj.repository.service.Impl;

import static com.sj.repository.util.RedisConstant.REVIEWCOUNT;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.sj.model.model.Product;
import com.sj.model.model.Review;
import com.sj.repository.repository.ReviewRepository;
import com.sj.repository.search.service.ProductSearchService;
import com.sj.repository.service.ReviewService;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewRepository repository;
	@Autowired
	private StringRedisTemplate template;
	@Autowired
	private ProductSearchService searchService;

	@Override
	public Page<Review> findByProduct(Product product, Pageable pageable) {
		return repository.findByProduct(product, pageable);
	}

	@Override
	public Review save(Review review) {
		review.setCreatedTime(Calendar.getInstance());
		review = repository.save(review);
		template.opsForValue().increment(
				REVIEWCOUNT + review.getProduct().getId(), 1);
		searchService.findOne(review.getProduct().getId());
		return review;
	}

}
