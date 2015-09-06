package com.sj.repository.converter;

import java.util.Locale;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.ScaleEnum;

public class StringToScaleEnumConverter implements Converter<String, ScaleEnum>{

	@Override
	public ScaleEnum convert(String source) {
		try {
			return ScaleEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			return null;
		}
	}

}
