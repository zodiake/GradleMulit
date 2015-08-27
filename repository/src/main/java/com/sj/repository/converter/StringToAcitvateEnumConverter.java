package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.ActivateEnum;

public class StringToAcitvateEnumConverter implements
		Converter<String, ActivateEnum> {

	@Override
	public ActivateEnum convert(String source) {
		ActivateEnum e = ActivateEnum.valueOf(source);
		return e;
	}
}
