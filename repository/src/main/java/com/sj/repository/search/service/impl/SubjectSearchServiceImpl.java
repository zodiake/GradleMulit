package com.sj.repository.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.repository.repository.SubjectSearchRepository;
import com.sj.repository.search.model.SubjectSearch;
import com.sj.repository.search.service.SubjectSearchService;

@Service
public class SubjectSearchServiceImpl implements SubjectSearchService {
	@Autowired
	private SubjectSearchRepository repository;

	@Override
	public Page<SubjectSearch> findByTitle(String title, Pageable pageable) {
		return repository.findByTitleOrderByCreatedTimeDesc(title, pageable);
	}

	@Override
	public void save(SubjectSearch search) {
		repository.save(search);
	}
}
