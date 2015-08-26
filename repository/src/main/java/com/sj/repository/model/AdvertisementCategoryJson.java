package com.sj.repository.model;

import com.sj.model.model.AdvertismentCategory;

public class AdvertisementCategoryJson {
	private Long id;
	private String name;

	public AdvertisementCategoryJson(AdvertismentCategory category) {
		this.id = category.getId();
		this.name = category.getName();
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
