package com.sj.repository.model;

import java.io.Serializable;

import com.sj.model.model.ProductCategory;

public class ProductCategoryJson implements Serializable {
	private Long id;
	private String name;
	private Long parent;

	public ProductCategoryJson(ProductCategory p) {
		this.id = p.getId();
		this.name = p.getName();
		this.parent = p.getParent().getId();
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

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}
}
