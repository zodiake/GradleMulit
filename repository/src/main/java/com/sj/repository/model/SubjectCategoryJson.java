package com.sj.repository.model;

import com.sj.model.model.SubjectCategory;

public class SubjectCategoryJson {
	private Long id;
	private String name;

	public SubjectCategoryJson() {
	}

	public SubjectCategoryJson(SubjectCategory sc) {
		this.id = sc.getId();
		this.name = sc.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
