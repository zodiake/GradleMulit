package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.InformationContent;

public class StringToInfoContentConverter implements
		Converter<String, InformationContent> {

	@Override
	public InformationContent convert(String source) {
		return new InformationContent(source);
	}

}
