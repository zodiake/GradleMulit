package com.sj.repository.converter;

import java.util.Locale;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.OutputEnum;

public class StringToOutputEnumConverter implements Converter<String, OutputEnum>{

	@Override
	public OutputEnum convert(String source) {
		try {
			return OutputEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			return null;
		}
	}

}
