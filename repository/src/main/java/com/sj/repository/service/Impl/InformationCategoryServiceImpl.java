package com.sj.repository.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.InformationCategory;
import com.sj.repository.repository.InformationCategoryRepository;
import com.sj.repository.service.InformationCategoryService;

@Service
@Transactional
public class InformationCategoryServiceImpl implements
	InformationCategoryService {
	@Autowired
	private InformationCategoryRepository repository;

	@Override
	public Page<InformationCategory> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public InformationCategory findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public InformationCategory save(InformationCategory category) {
		return repository.save(category);
	}

	@Override
	public InformationCategory update(InformationCategory category) {
		return repository.save(category);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public InformationCategory findByUrl(String category) {
		return repository.findByUrl(category);
	}

}
