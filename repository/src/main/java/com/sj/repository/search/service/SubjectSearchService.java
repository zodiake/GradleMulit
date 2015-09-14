package com.sj.repository.search.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.repository.search.model.SubjectSearch;

public interface SubjectSearchService {
	Page<SubjectSearch> findByTitle(String title, Pageable pageable);

	void save(SubjectSearch search);
}
