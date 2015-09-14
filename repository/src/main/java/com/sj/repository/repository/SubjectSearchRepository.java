package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.sj.repository.search.model.SubjectSearch;

public interface SubjectSearchRepository extends
		ElasticsearchCrudRepository<SubjectSearch, Long> {
	Page<SubjectSearch> findByTitleOrderByCreatedTimeDesc(String title,
			Pageable pageable);
}
