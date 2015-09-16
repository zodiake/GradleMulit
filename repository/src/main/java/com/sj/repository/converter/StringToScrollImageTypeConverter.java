package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.ScrollImageType;

public class StringToScrollImageTypeConverter implements
		Converter<String, ScrollImageType> {

	@Override
	public ScrollImageType convert(String source) {
		return ScrollImageType.fromString(source);
	}

}
