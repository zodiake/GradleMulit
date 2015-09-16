package com.sj.repository.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.City;
import com.sj.repository.service.CityService;

public class LongToCityConverter implements Converter<Long, City> {
	@Autowired
	private CityService service;

	@Override
	public City convert(Long source) {
		return service.findOne(source);
	}

}
