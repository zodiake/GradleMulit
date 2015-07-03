package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.OutputEnum;

public class StringToOutputEnumConverter implements Converter<String, OutputEnum>{

	@Override
	public OutputEnum convert(String source) {
		return OutputEnum.valueOf(source);
	}

}
