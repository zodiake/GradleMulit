package com.sj.admin.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.ActivateEnum;

public class StringToAcitvateEnumConverter implements
		Converter<String, ActivateEnum> {

	@Override
	public ActivateEnum convert(String source) {
		return ActivateEnum.valueOf(source);
	}
}
