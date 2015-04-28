package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Product;
import com.sj.model.model.Review;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long>{
	Page<Review> findByProduct(Product p,Pageable pageable);
}
