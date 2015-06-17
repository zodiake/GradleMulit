package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.OriginalEnum;

public class StringToOriginalEnumConverter implements
		Converter<String, OriginalEnum> {

	@Override
	public OriginalEnum convert(String source) {
		return OriginalEnum.fromString(source);
	}

}
