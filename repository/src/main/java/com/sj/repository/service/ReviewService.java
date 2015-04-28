package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Product;
import com.sj.model.model.Review;

public interface ReviewService {
	Page<Review> findByProduct(Product product, Pageable pageable);

	Review save(Review review);
}
