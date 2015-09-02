package com.sj.repository.converter;

import java.util.Locale;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.PlaceEnum;

public class StringToPlaceEnumConverter implements
		Converter<String, PlaceEnum> {

	@Override
	public PlaceEnum convert(String source) {
		try {
			return PlaceEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			return null;
		}
	}
}
