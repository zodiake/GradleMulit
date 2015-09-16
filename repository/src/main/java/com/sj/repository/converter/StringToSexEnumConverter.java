package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.SexEnum;

public class StringToSexEnumConverter implements
		Converter<String, SexEnum> {

	@Override
	public SexEnum convert(String source) {
		return SexEnum.valueOf(source);
	}
}
