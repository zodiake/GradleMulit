package com.sj.repository.model;

import com.sj.model.model.ProductCategory;

public class ProductCategoryJson {
	private Long id;
	private String name;

	public ProductCategoryJson(ProductCategory p) {
		this.id = p.getId();
		this.name = p.getName();
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
