package com.sj.repository.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Product;
import com.sj.model.model.Review;
import com.sj.repository.repository.ReviewRepository;
import com.sj.repository.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewRepository repository;

	@Override
	public Page<Review> findByProduct(Product product, Pageable pageable) {
		return repository.findByProduct(product, pageable);
	}

	@Override
	public Review save(Review review) {
		return repository.save(review);
	}

}
