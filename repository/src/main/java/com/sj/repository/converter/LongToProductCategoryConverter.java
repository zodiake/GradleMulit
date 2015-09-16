package com.sj.repository.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.sj.model.model.ProductCategory;
import com.sj.repository.service.ProductCategoryService;

public class LongToProductCategoryConverter implements
		Converter<Long, ProductCategory> {
	@Autowired
	private ProductCategoryService service;

	@Override
	public ProductCategory convert(Long source) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		return service.findOne(source);
	}
}
