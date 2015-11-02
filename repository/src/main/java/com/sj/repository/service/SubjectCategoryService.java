package com.sj.repository.service;

import java.util.List;

import com.sj.model.model.SubjectCategory;
import com.sj.repository.model.SubjectCategoryJson;

public interface SubjectCategoryService {
	public SubjectCategory findOne(Long id);

	public List<SubjectCategoryJson> findAllJson();

	public List<SubjectCategory> findAll();
	
	public List<SubjectCategory> findByParent();
	
}
