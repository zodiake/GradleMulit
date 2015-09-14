package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.SubjectCategory;

public class StringToSubjectCategoryConverter implements
		Converter<String, SubjectCategory> {

	@Override
	public SubjectCategory convert(String source) {
		try {
			return new SubjectCategory(Long.parseLong(source));
		} catch (Exception e) {
			return null;
		}
	}
}
