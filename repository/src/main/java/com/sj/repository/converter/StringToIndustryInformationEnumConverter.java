package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.IndustryInformationEnum;

public class StringToIndustryInformationEnumConverter implements Converter<String, IndustryInformationEnum>{

	@Override
	public IndustryInformationEnum convert(String source) {
		return IndustryInformationEnum.valueOf(source);
	}

}
