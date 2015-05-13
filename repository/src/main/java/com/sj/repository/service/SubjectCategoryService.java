package com.sj.repository.service;

import com.sj.model.model.SubjectCategory;

public interface SubjectCategoryService {
	public SubjectCategory save(SubjectCategory sc);
	
	public SubjectCategory findOne(Long id);
}
