package com.sj.repository.service.Impl;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.sj.model.type.AdvertiseCategoryEnum;
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
	public Page<Information> findByCategory(InformationCategory category,
			Pageable pageable) {

		return repository.findByCategory(category, pageable);
	}

	@Override
	public Information update(Information information) {
		Information info = repository.findOne(information.getId());
		info.setTitle(information.getTitle());
		info.setContent(information.getContent());
		info.setCategory(information.getCategory());
		info.setSummary(information.getSummary());
		return info;
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
	public List<Information> findByCategoryAndShowOnIndex(
			InformationCategory category) {
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
	public void updateState(Long id, ActivateEnum state) {
		Information info = repository.findOne(id);
		info.setActivate(state);
	}
}
