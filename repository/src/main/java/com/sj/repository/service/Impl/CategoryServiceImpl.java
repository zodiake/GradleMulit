package com.sj.repository.service.Impl;

import java.util.Calendar;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj.model.model.Category;
import com.sj.repository.repository.CategoryRepository;
import com.sj.repository.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository repository;

	@Override
	public Category findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Category> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<Category> findByParent(Pageable pageable, Category category) {
		return repository.findByParent(pageable, category);
	}

	@Override
	public Category save(Category category) {
		category.setCreatedTime(Calendar.getInstance());
		return repository.save(category);
	}

	@Override
	public Category findByIdAndParent(Long id, Category category) {
		return repository.findByIdAndParent(id, category);
	}

	@Override
	public Category update(Category category) {
		Category memory = repository.findOne(category.getId());
		memory.setName(category.getName());
		memory.setActivate(category.getActivate());
		memory.setUpdatedBy(category.getUpdatedBy());
		memory.setUpdatedTime(Calendar.getInstance());
		return repository.save(memory);
	}
}
