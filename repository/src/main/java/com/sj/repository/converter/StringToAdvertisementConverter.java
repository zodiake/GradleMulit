package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.AdvertisementContent;

public class StringToAdvertisementConverter implements
		Converter<String, AdvertisementContent> {

	@Override
	public AdvertisementContent convert(String source) {
		AdvertisementContent content = new AdvertisementContent();
		content.setContent(source);
		return content;
	}
}