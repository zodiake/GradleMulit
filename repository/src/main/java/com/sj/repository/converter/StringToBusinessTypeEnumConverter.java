package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.BusinessTypeEnum;

public class StringToBusinessTypeEnumConverter implements
		Converter<String, BusinessTypeEnum> {

	@Override
	public BusinessTypeEnum convert(String source) {
		return BusinessTypeEnum.valueOf(source);
	}

}
