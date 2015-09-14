package com.sj.repository.search.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.repository.repository.InfoSearchRepository;
import com.sj.repository.search.model.InfoSearch;
import com.sj.repository.search.model.InfoSearchOption;
import com.sj.repository.search.service.InfoSearchService;

@Service
public class InfoSearchServiceImpl implements InfoSearchService {
	@Autowired
	private InfoSearchRepository repository;

	@Override
	public Page<InfoSearch> findByOption(InfoSearchOption option,
			Pageable pageable) {
		if (StringUtils.isNotEmpty(option.getTitle()))
			return repository.findByTitleOrderByCreatedTimeDesc(
					option.getTitle(), pageable);
		else
			return repository.findAll(pageable);
	}

	@Override
	public void save(InfoSearch info) {
		repository.save(info);
	}

	@Override
	public Map<String, String> buildMap(InfoSearchOption option) {
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

	public List<Field> filterNullValue(InfoSearchOption option, Field[] fields) {
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
