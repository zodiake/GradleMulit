package com.sj.repository.search.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.repository.search.model.InfoSearch;

public interface InfoSearchService {
	Page<InfoSearch> findByTitle(String title, Pageable pageable);

	void save(InfoSearch info);
}
