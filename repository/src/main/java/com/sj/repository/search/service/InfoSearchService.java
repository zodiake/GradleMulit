package com.sj.repository.search.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.repository.search.model.InfoSearch;
import com.sj.repository.search.model.InfoSearchOption;

public interface InfoSearchService {
	Page<InfoSearch> findByOption(InfoSearchOption option, Pageable pageable);

	void save(InfoSearch info);

	Map<String, String> buildMap(InfoSearchOption option);
	
	void delete(InfoSearch info);
	
	void update(InfoSearch info);
}
