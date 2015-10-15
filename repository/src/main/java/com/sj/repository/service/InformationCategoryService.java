package com.sj.repository.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.InformationCategory;
import com.sj.repository.model.InformationCategoryJson;

public interface InformationCategoryService {

	public Page<InformationCategory> findAll(Pageable pageable);

	public List<InformationCategory> findAll();

	public InformationCategory findOne(Long id);

	public InformationCategory save(InformationCategory category);

	public InformationCategory update(InformationCategory category);

	public void delete(Long id);

	public Page<InformationCategoryJson> findAllJson();
}
