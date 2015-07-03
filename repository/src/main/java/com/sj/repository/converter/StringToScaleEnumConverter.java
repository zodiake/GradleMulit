package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.ScaleEnum;

public class StringToScaleEnumConverter implements Converter<String, ScaleEnum>{

	@Override
	public ScaleEnum convert(String source) {
		return ScaleEnum.valueOf(source);
	}

}
