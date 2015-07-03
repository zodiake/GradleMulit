package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.PositionEnum;

public class StringToPositionEnumConverter implements Converter<String, PositionEnum>{

	@Override
	public PositionEnum convert(String source) {
		
		return PositionEnum.valueOf(source);
	}

}
