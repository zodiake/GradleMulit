package com.sj.repository.converter;

import java.util.Locale;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.SexEnum;

public class StringToSexEnumConverter implements
		Converter<String, SexEnum> {

	@Override
	public SexEnum convert(String source) {
		try {
			return SexEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			return null;
		}
	}
}
