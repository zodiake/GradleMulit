package com.sj.repository.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.Province;
import com.sj.repository.service.ProvinceService;

public class LongToProvinceConverter implements Converter<Long, Province> {
	@Autowired
	private ProvinceService provinceService;

	@Override
	public Province convert(Long source) {
		return provinceService.findOne(source);
	}

}
