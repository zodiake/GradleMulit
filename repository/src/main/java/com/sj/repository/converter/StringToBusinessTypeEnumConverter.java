package com.sj.repository.converter;

import java.util.Locale;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.BusinessTypeEnum;
import com.sj.model.type.ComponyTypeEnum;

public class StringToBusinessTypeEnumConverter implements
		Converter<String, BusinessTypeEnum> {

	@Override
	public BusinessTypeEnum convert(String source) {
		try {
			return BusinessTypeEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			return null;
		}
	}

}
