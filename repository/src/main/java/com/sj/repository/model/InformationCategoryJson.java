package com.sj.repository.model;

import com.sj.model.model.InformationCategory;

public class InformationCategoryJson {
	private Long id;
	private String name;

	public InformationCategoryJson(InformationCategory c) {
		this.id = c.getId();
		this.name = c.getName();
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
