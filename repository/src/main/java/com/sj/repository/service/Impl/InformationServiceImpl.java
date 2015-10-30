package com.sj.repository.service.Impl;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import net.sf.ehcache.management.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sj.model.model.Information;
import com.sj.model.model.InformationCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.InformationJson;
import com.sj.repository.repository.InformationCategoryRepository;
import com.sj.repository.repository.InformationRepository;
import com.sj.repository.search.model.InfoSearch;
import com.sj.repository.search.service.InfoSearchService;
import com.sj.repository.service.InformationService;

@Service
@Transactional
public class InformationServiceImpl implements InformationService {
	@Autowired
	private InformationRepository repository;
	@Autowired
	private InformationCategoryRepository informationCategoryRrepository;
	@Autowired
	private InfoSearchService infoSearchService;

	@Override
	@Cacheable(value = "informationsCache", key = "#category.id + #pageable.getPageNumber()")
	public Page<Information> findByCategory(InformationCategory category,
			Pageable pageable) {
		
		return repository.findByCategoryAndActivate(category, pageable,
				ActivateEnum.ACTIVATE);
	}

	@Override
	@CacheEvict(value = {"informationsCache","indexInformationCache"},allEntries = true)
	@CachePut(value = "informationCache",key = "#information.id")
	public Information update(Information information) {
		Information info = repository.findOne(information.getId());
		info.setTitle(information.getTitle());
		info.setContent(information.getContent());
		info.setCategory(information.getCategory());
		info.setSummary(information.getSummary());
		info.setUpdatedTime(Calendar.getInstance());
		return info;
	}

	@Override
	@CacheEvict(value = {"informationsCache","indexInformationCache"},allEntries = true)
	public Information create(Information info) {
		info.setCreatedTime(Calendar.getInstance());
		return repository.save(info);
	}

	@Override
	@Cacheable(value = "informationCache",key = "#id")
	public Information findOne(Long id) {
		Information info = repository.findOne(id);
		return info;
	}

	@Override
	@Cacheable(value = "indexInformationCache", key = "#category.id")
	public List<Information> findByCategoryAndShowOnIndex(InformationCategory category) {
		Page<Information> infoPage = repository.findByCategoryAndActivate(
				category, new PageRequest(0, 3, Direction.DESC, "createdTime"),
				ActivateEnum.ACTIVATE);
		return infoPage.getContent();
	}

	@Override
	@CacheEvict(value = {"informationsCache","indexInformationCache"},allEntries = true)
	public Information save(Information advertisement) {
		advertisement.setUpdatedTime(Calendar.getInstance());
		advertisement.setCreatedTime(Calendar.getInstance());
		Information info = repository.save(advertisement);
		infoSearchService.save(new InfoSearch(info));
		return info;
	}

	@Override
	public Page<Information> findByActivate(Pageable pageable,
			ActivateEnum activate) {
		return repository.findByActivateOrderByUpdatedTimeDesc(pageable,
				activate);
	}

	@Override
	public Page<InformationJson> findAllJson(Pageable pageable,
			ActivateEnum activate) {
		Page<Information> pages;
		if (activate == null)
			pages = repository.findByOrderByUpdatedTimeDesc(pageable);
		else
			pages = findByActivate(pageable, activate);
		List<InformationJson> lists = pages.getContent().stream()
				.map(c -> new InformationJson(c)).collect(Collectors.toList());
		return new PageImpl<InformationJson>(lists, pageable,
				pages.getTotalElements());
	}

	@Override
	@CacheEvict(value = {"informationsCache","indexInformationCache","informationCache"},allEntries = true)
	public void updateState(Long id, ActivateEnum state) {
		Information info = repository.findOne(id);
		info.setActivate(state);
	}
}
