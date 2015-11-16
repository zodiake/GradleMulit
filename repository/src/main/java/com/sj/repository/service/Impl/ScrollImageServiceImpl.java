package com.sj.repository.service.Impl;

import java.util.Calendar;
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
import com.sj.model.type.ActivateEnum;
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
	@Cacheable(value = "indexImageCache")
	public List<ScrollImage> findAll() {
		List<ScrollImage> images = repository.findByStateOrderBySortNumberDesc(ActivateEnum.ACTIVATE);
		return images;
	}
	
	@Override
	public List<ScrollImage> findAllJson() {
		List<ScrollImage> images = repository.findByStateOrderBySortNumberDesc(ActivateEnum.ACTIVATE);
		return images;
	}

	@Override
	@CacheEvict(value = { "scrollImageCache", "indexImageCache" }, allEntries = true)
	public ScrollImage update(Long id, ScrollImage image) {
		ScrollImage img = repository.findOne(id);
		img.setImageUrl(image.getImageUrl());
		img.setHref(image.getHref());
		img.setUpdatedTime(Calendar.getInstance());
		return img;
	}

	@Override
	@Caching(evict = @CacheEvict(value = "scrollImageCache", key = "#type"), put = @CachePut(value = "scrollImageCache", key = "#type"))
	public void freshCache(ScrollImageType type) {
	}

	@Override
	@CacheEvict(value = { "scrollImageCache", "indexImageCache" }, allEntries = true)
	public void save(ScrollImage image) {
		image.setCreatedTime(Calendar.getInstance());
		image.setUpdatedTime(Calendar.getInstance());
		image.setScrollType(ScrollImageType.INDEX);
		image.setSortNumber(0);
		image.setState(ActivateEnum.ACTIVATE);
		repository.save(image);
	}

	@Override
	@CacheEvict(value = { "scrollImageCache", "indexImageCache" }, allEntries = true)
	public void updateState(Long id) {
		ScrollImage image = repository.findOne(id);
		if (image.getState() == ActivateEnum.ACTIVATE) {
			image.setState(ActivateEnum.DEACTIVATE);
			image.setUpdatedTime(Calendar.getInstance());
		} else
			image.setState(ActivateEnum.ACTIVATE);
		repository.save(image);
	}
}
