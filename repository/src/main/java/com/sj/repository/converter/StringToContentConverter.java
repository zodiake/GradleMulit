package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.Content;

public class StringToContentConverter implements Converter<String, Content> {

	@Override
	public Content convert(String source) {
		return new Content(source);
	}

}
