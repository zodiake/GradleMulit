package com.sj.repository.service.Impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Brand;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.BrandJson;
import com.sj.repository.repository.BrandRepository;
import com.sj.repository.search.model.BrandSearchOption;
import com.sj.repository.service.BrandService;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandRepository repository;
	@Autowired
	private EntityManager em;

	@Override
	public List<Brand> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Brand findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	@Cacheable(value = "indexBrandCache")
	public List<Brand> findByAcitvate(ActivateEnum activate, Pageable pageable) {
		return repository.findByActivate(activate, pageable);
	}

	@Override
	@CacheEvict(value = {"brandCache","indexBrandCache"},allEntries = true)
	public Brand save(Brand brand) {
		Calendar c = Calendar.getInstance();
		brand.setCreatedTime(c);
		return repository.save(brand);
	}

	@Override
	@CacheEvict(value = {"brandCache","indexBrandCache"},allEntries = true)
	public void update(Brand brand) {
		em.createQuery(
				"update Brand b set b.name=:name,b.coverImg=:cover where b.id=:id")
				.setParameter("name", brand.getName())
				.setParameter("cover", brand.getCoverImg())
				.setParameter("id", brand.getId()).executeUpdate();
	}

	@Override
	@Cacheable(value = "brandCache")
	public List<Brand> findByAcitvate(ActivateEnum activate) {
		return repository.findByActivate(activate);
	}

	@Override
	public Page<BrandJson> findByActivateToJson(ActivateEnum activate,
			Pageable pageable) {
		Page<Brand> pages;
		if (activate == null)
			pages = repository.findAll(pageable);
		else
			pages = repository.findByActivate(pageable, activate);
		List<BrandJson> lists = pages.getContent().stream()
				.map(c -> new BrandJson(c)).collect(Collectors.toList());
		return new PageImpl<BrandJson>(lists, pageable,
				pages.getTotalElements());
	}

	@Override
	@CacheEvict(value = {"brandCache","indexBrandCache"},allEntries = true)
	public void activate(Long id, ActivateEnum activate) {
		em.createQuery("update Brand b set b.activate=:activate where b.id=:id")
				.setParameter("activate", activate).setParameter("id", id)
				.executeUpdate();
	}
	
	@Override
	public Page<Brand> searchBrand(BrandSearchOption option,Pageable pageable) {
		return repository.findByNameLike("%"+option.getTitle()+"%", pageable);
	}
	@Override
	public Map<String, String> buildMap(BrandSearchOption option) {
		Field[] fields = option.getClass().getDeclaredFields();
		List<Field> options = filterNullValue(option, fields);
		Map<String, String> map = new HashMap<>();
		for (Field f : options) {
			Object value;
			try {
				value = f.get(option);
				map.put(f.getName(), String.valueOf(value));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	public List<Field> filterNullValue(BrandSearchOption option,
			Field[] fields) {
		return Arrays.stream(fields).filter(f -> {
			if (!Modifier.isPublic(f.getModifiers())) {
				f.setAccessible(true);
			}
			try {
				return f.get(option) != null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList());
	}
}
