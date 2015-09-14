package com.sj.repository.search.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.repository.search.model.SubjectSearch;
import com.sj.repository.search.model.SubjectSearchOption;

public interface SubjectSearchService {
	Page<SubjectSearch> findByOption(SubjectSearchOption option,
			Pageable pageable);

	void save(SubjectSearch search);

	public Map<String, String> buildMap(SubjectSearchOption option);
}
