package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.ComponyTypeEnum;

public class StringToComponyTypeEnumConverter implements Converter<String, ComponyTypeEnum> {

	@Override
	public ComponyTypeEnum convert(String source) {
		return ComponyTypeEnum.valueOf(source);
	}

}
