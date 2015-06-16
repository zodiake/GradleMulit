package com.sj.repository.converter;

import org.h2.util.StringUtils;
import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.SubjectCategory;

public class StringToSubjectCategoryConverter implements
		Converter<String, SubjectCategory> {

	@Override
	public SubjectCategory convert(String source) {
		if (!StringUtils.isNullOrEmpty(source)) {
			try {
				Long id = Long.valueOf(source);
				return new SubjectCategory(id);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

}
