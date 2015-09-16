package com.sj.repository.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.Brand;
import com.sj.repository.service.BrandService;

public class LongToBrandConverter implements Converter<Long, Brand> {
	@Autowired
	private BrandService service;

	@Override
	public Brand convert(Long source) {
		return service.findOne(source);
	}

}
