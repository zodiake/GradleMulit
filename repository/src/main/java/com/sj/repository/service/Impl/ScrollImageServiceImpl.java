package com.sj.repository.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.ScrollImage;
import com.sj.model.type.ScrollImageType;
import com.sj.repository.repository.ScrollImageRepository;
import com.sj.repository.service.ScrollImageService;

@Service
@Transactional
public class ScrollImageServiceImpl implements ScrollImageService {
	@Autowired
	private ScrollImageRepository repository;

	@Override
	@Cacheable(value = "scrollImageCache", key = "#type")
	public List<ScrollImage> findAll(ScrollImageType type, Pageable pageable) {
		return repository.findByScrollType(type, pageable);
	}

	@Override
	public ScrollImage update(ScrollImage image) {
		ScrollImage img = repository.findOne(image.getId());
		img.setImageUrl(image.getImageUrl());
		img.setHref(image.getHref());
		return repository.save(img);
	}

	@Override
	@Caching(evict = @CacheEvict(value = "scrollImageCache", key = "#type"), put = @CachePut(value = "scrollImageCache", key = "#type"))
	public void freshCache(ScrollImageType type) {
	}
}
