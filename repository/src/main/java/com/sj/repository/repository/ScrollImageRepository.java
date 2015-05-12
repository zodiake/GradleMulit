package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.ScrollImage;
import com.sj.model.type.ScrollImageType;

public interface ScrollImageRepository extends
		PagingAndSortingRepository<ScrollImage, Long> {
	List<ScrollImage> findByScrollType(ScrollImageType type, Pageable pageable);
}
