package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.ProductDisplay;
import com.sj.model.type.ActivateEnum;

public interface ProductDisplayRepostiory extends
	PagingAndSortingRepository<ProductDisplay, Long> {
	public List<ProductDisplay> findByState(ActivateEnum state);
	
	public Long countByState(ActivateEnum state);
	
}
