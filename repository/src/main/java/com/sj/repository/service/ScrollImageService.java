package com.sj.repository.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sj.model.model.ScrollImage;
import com.sj.model.type.ScrollImageType;

public interface ScrollImageService {
	public List<ScrollImage> findAll(ScrollImageType type, Pageable pageable);

	public ScrollImage update(Long id, ScrollImage image);

	public void freshCache(ScrollImageType type);

	public List<ScrollImage> findAll();
	
	public void save(ScrollImage image);
	
	public void updateState(Long id);
}
