package com.sj.repository.service.Impl;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.sj.model.model.Brand;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.BrandJson;
import com.sj.repository.repository.BrandRepository;
import com.sj.repository.service.BrandService;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandRepository repository;
	@Autowired
	private EntityManager em;

	@Override
	public Page<Brand> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<Brand> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Brand findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Brand> findByAcitvate(ActivateEnum activate, Pageable pageable) {
		return repository.findByActivate(activate, pageable);
	}

	@Override
	public Brand save(Brand brand) {
		Calendar c = Calendar.getInstance();
		brand.setCreatedTime(c);
		return repository.save(brand);
	}

	@Override
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
	public Brand findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public void deleteOne(Long id) {
		repository.delete(id);
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
	public void activate(Long id, ActivateEnum activate) {
		em.createQuery("update Brand b set b.activate=:activate where b.id=:id")
				.setParameter("activate", activate).setParameter("id", id)
				.executeUpdate();
	}

	@Override
	public List<Brand> findAllOrderByName() {
		return repository.findAll(new Sort(new Order(Direction.ASC,"name")));
	}
}
