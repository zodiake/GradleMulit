package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.PositionInformationEnum;

public class StringToPositionInformationEnumConverter implements Converter<String, PositionInformationEnum>{

	@Override
	public PositionInformationEnum convert(String source) {
		return PositionInformationEnum.valueOf(source);
	}

}
