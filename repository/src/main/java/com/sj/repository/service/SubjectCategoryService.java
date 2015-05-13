package com.sj.repository.service;

import java.util.List;

import com.sj.model.model.SubjectCategory;
import com.sj.model.type.ActivateEnum;

public interface SubjectCategoryService {
	public SubjectCategory save(SubjectCategory sc);
	
	public SubjectCategory findOne(Long id);
	
	public List<SubjectCategory> findByActivate(ActivateEnum activate);
}
