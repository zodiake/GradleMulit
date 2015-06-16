package com.sj.repository.service.Impl;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.repository.CategoryRepository;
import com.sj.repository.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository repository;

	@Override
	public ProductCategory findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<ProductCategory> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<ProductCategory> findByParent(Pageable pageable,
			ProductCategory category) {
		return repository.findByParent(pageable, category);
	}

	@Override
	public ProductCategory save(ProductCategory category) {
		category.setCreatedTime(Calendar.getInstance());
		return repository.save(category);
	}

	@Override
	public ProductCategory findByIdAndParent(Long id, ProductCategory category) {
		return repository.findByIdAndParent(id, category);
	}

	@Override
	public ProductCategory update(ProductCategory category) {
		ProductCategory memory = repository.findOne(category.getId());
		memory.setName(category.getName());
		memory.setActivate(category.getActivate());
		memory.setUpdatedBy(category.getUpdatedBy());
		memory.setUpdatedTime(Calendar.getInstance());
		return repository.save(memory);
	}

	@Override
	public List<ProductCategory> findByParentAndActivate(
			ProductCategory category, ActivateEnum activate) {
		return repository.findByParentAndActivate(category, activate);
	}

	@Override
	public List<ProductCategory> findAllSecondCategory(ActivateEnum activate) {
		List<ProductCategory> categories = repository.findByParentAndActivate(
				null, activate);
		return categories.stream().flatMap((c) -> c.getCategories().stream())
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductCategory> findAllFirstCategory(ActivateEnum activate) {
		return repository.findByParentAndActivate(null, activate);
	}
}
