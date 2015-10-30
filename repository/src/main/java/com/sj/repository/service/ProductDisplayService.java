package com.sj.repository.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.ProductDisplay;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.ProductDisplayJson;

public interface ProductDisplayService {
	public List<ProductDisplay> findAll();
	
	public Page<ProductDisplayJson> findAllJson(Pageable pageable);
	
	public String updateState(Long id,ActivateEnum activate);
	
	public String save(Long productId);
}
