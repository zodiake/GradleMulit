package com.sj.repository.converter;

import java.util.Locale;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.ComponyTypeEnum;

public class StringToComponyTypeEnumConverter implements Converter<String, ComponyTypeEnum> {

	@Override
	public ComponyTypeEnum convert(String source) {
		try {
			return ComponyTypeEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			return null;
		}
	}
}
