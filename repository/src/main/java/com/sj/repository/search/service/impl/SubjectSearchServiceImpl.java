package com.sj.repository.search.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.repository.repository.SubjectSearchRepository;
import com.sj.repository.search.model.SubjectSearch;
import com.sj.repository.search.model.SubjectSearchOption;
import com.sj.repository.search.service.SubjectSearchService;

@Service
public class SubjectSearchServiceImpl implements SubjectSearchService {
	@Autowired
	private SubjectSearchRepository repository;

	@Override
	public void save(SubjectSearch search) {
		repository.save(search);
	}

	@Override
	public Page<SubjectSearch> findByOption(SubjectSearchOption option,
			Pageable pageable) {
		return repository.findByTitleOrderByCreatedTimeDesc(option.getTitle(),
				pageable);
	}

	@Override
	public Map<String, String> buildMap(SubjectSearchOption option) {
		Field[] fields = option.getClass().getDeclaredFields();
		List<Field> options = filterNullValue(option, fields);
		Map<String, String> map = new HashMap<>();
		for (Field f : options) {
			Object value;
			try {
				value = f.get(option);
				map.put(f.getName(), String.valueOf(value));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public List<Field> filterNullValue(SubjectSearchOption option,
			Field[] fields) {
		return Arrays.stream(fields).filter(f -> {
			if (!Modifier.isPublic(f.getModifiers())) {
				f.setAccessible(true);
			}
			try {
				return f.get(option) != null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList());
	}
}
