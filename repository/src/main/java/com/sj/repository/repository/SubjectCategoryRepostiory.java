package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sj.model.model.SubjectCategory;
import com.sj.model.type.ActivateEnum;

public interface SubjectCategoryRepostiory extends
		CrudRepository<SubjectCategory, Long> {
	List<SubjectCategory> findByActivateAndParent(ActivateEnum activate,SubjectCategory sc);
}
