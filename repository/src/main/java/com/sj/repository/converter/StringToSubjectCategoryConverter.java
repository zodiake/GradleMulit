package com.sj.repository.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.SubjectCategory;
import com.sj.repository.service.SubjectCategoryService;

public class StringToSubjectCategoryConverter implements
		Converter<String, SubjectCategory> {
	@Autowired
	private SubjectCategoryService categoryService;

	@Override
	public SubjectCategory convert(String source) {
		try {
			return categoryService.findOne(Long.parseLong(source));
		} catch (Exception e) {
			return null;
		}
	}
}
