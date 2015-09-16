package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.Product;

public class StringToProductConverter implements Converter<String, Product> {

	@Override
	public Product convert(String source) {
		try {
			Long id = Long.valueOf(source);
			return new Product(id);
		} catch (Exception e) {
			return null;
		}
	}

}
