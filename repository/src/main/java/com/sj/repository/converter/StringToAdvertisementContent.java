package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.InformationContent;

public class StringToAdvertisementContent implements
		Converter<String, InformationContent> {

	@Override
	public InformationContent convert(String source) {
		InformationContent content = new InformationContent();
		content.setContent(source);
		return content;
	}
}
