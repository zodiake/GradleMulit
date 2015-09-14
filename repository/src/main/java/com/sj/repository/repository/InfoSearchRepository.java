package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.sj.repository.search.model.InfoSearch;

public interface InfoSearchRepository extends
		ElasticsearchCrudRepository<InfoSearch, Long> {
	Page<InfoSearch> findByTitle(String title, Pageable pageable);
}
