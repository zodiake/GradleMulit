package com.sj.repository.converter;


import org.springframework.core.convert.converter.Converter;

public class StringToFloatConverter implements
		Converter<String, Float> {

	@Override
	public Float convert(String source) {
		try {
			return Float.valueOf(source);
		} catch (Exception e) {
			return null;
		}
	}
}
