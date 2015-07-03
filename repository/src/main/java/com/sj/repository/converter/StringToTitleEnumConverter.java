package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.TitleEnum;

public class StringToTitleEnumConverter implements Converter<String, TitleEnum>{

	@Override
	public TitleEnum convert(String source) {
		return TitleEnum.valueOf(source);
	}
	
}
