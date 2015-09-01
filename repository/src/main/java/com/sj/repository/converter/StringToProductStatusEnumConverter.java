package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.ProductStatusEnum;

public class StringToProductStatusEnumConverter implements
		Converter<String, ProductStatusEnum> {

	@Override
	public ProductStatusEnum convert(String source) {
		return ProductStatusEnum.stringToEnum(source);
	}

}
