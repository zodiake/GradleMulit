package com.sj.repository.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.SubjectCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.repository.SubjectCategoryRepository;
import com.sj.repository.service.SubjectCategoryService;

@Service
public class SubjectCategoryServiceImpl implements SubjectCategoryService {
	@Autowired
	private SubjectCategoryRepository repository;

	@Override
	public SubjectCategory save(SubjectCategory sc) {
		return repository.save(sc);
	}

	@Override
	public SubjectCategory findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<SubjectCategory> findByActivate(ActivateEnum activate) {
		return repository.findByActivate(activate);
	}

}
