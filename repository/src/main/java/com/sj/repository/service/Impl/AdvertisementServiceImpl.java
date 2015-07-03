package com.sj.repository.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Advertisement;
import com.sj.model.type.AdvertiseCategoryEnum;
import com.sj.repository.repository.AdvertisementRepository;
import com.sj.repository.service.AdvertisementService;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
	@Autowired
	private AdvertisementRepository repository;
	

	@Override
	@Cacheable(value = "advertiseCache", key = "#category")
	public List<Advertisement> findByCategory(AdvertiseCategoryEnum category,
			Pageable pageable) {
		List<Advertisement> page = repository
				.findByCategory(category, pageable);
		return page;
	}

	@Override
	@Caching(evict = @CacheEvict(value = "advertiseCache", key = "#advertisement.category"), put = @CachePut(value = "advertiseCache", key = "#advertisement.category"))
	public Advertisement update(Advertisement advertisement) {
		Advertisement result = repository.findOne(advertisement.getId());
		if (result.getContent() != null)
			result.getContent().setContent(
					advertisement.getContent().getContent());
		else
			result.setContent(advertisement.getContent());
		if (advertisement.getCoverImg() != null)
			result.setCoverImg(advertisement.getCoverImg());
		result.setDescription(advertisement.getDescription());
		return repository.save(result);
	}

	@Override
	public Advertisement findByIdAndCategory(Long id,
			AdvertiseCategoryEnum category) {
		return repository.findByIdAndCategory(id, category);
	}

	@Override
	public Advertisement save(Advertisement advertisement) {
		
		return null;
	}

}
