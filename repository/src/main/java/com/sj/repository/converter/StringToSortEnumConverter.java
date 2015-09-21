package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.repository.search.model.SortEnum;

public class StringToSortEnumConverter implements Converter<String, SortEnum> {

	@Override
	public SortEnum convert(String source) {
		return SortEnum.fromString(source);
	}

}
