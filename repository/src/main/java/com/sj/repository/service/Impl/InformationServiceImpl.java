package com.sj.repository.service.Impl;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sj.model.model.Information;
import com.sj.model.model.InformationCategory;
import com.sj.model.type.AdvertiseCategoryEnum;
import com.sj.repository.repository.InformationCategoryRepository;
import com.sj.repository.repository.InformationRepository;
import com.sj.repository.service.InformationService;

@Service
@Transactional
public class InformationServiceImpl implements InformationService {
	@Autowired
	private InformationRepository repository;
	@Autowired
	private InformationCategoryRepository informationCategoryRrepository;

	@Override
	public Page<Information> findByCategory(InformationCategory category,
			Pageable pageable) {

		return repository.findByCategory(category, pageable);
	}

	@Override
	@Caching(evict = @CacheEvict(value = "advertiseCache", key = "#advertisement.category"), put = @CachePut(value = "advertiseCache", key = "#advertisement.category"))
	public Information update(Information information) {
		Information result = repository.findOne(information.getId());
		if (result.getContent() != null)
			result.getContent().setContent(
					information.getContent().getContent());
		else
			result.setContent(information.getContent());
		return repository.save(result);
	}

	@Override
	public Information findByIdAndCategory(Long id,
			AdvertiseCategoryEnum category) {
		return repository.findByIdAndCategory(id, category);
	}

	@Override
	public Information create(Information info) {
		info.setCreatedTime(Calendar.getInstance());
		return repository.save(info);
	}

	@Override
	public Information findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	@Cacheable(value = "informationCache", key = "#category.id")
	public List<Information> findByCategoryAndShowOnIndex(InformationCategory category) {
		Page<Information> infoPage = repository.findByCategory(category,
				new PageRequest(0, 3, Direction.DESC, "createdTime"));
		return infoPage.getContent();
	}

	@Override
	public Page<Information> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Information save(Information advertisement) {
		return repository.save(advertisement);
	}

}
