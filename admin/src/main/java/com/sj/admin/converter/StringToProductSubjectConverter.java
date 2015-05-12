package com.sj.admin.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.Product;
import com.sj.model.model.ProductSubject;
import com.sj.repository.service.ProductService;

public class StringToProductSubjectConverter implements
		Converter<String, ProductSubject> {
	@Autowired
	private ProductService productService;

	@Override
	public ProductSubject convert(String source) {
		Product p = productService.findOne(Long.valueOf(source));
		return new ProductSubject(p);
	}

}
