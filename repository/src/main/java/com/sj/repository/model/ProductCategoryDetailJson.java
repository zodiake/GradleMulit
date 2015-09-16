package com.sj.repository.model;

import java.util.List;
import java.util.stream.Collectors;

import com.sj.model.model.ProductCategory;

public class ProductCategoryDetailJson {
	private Long id;
	private String name;
	private List<ProductCategoryJson> categories;

	public ProductCategoryDetailJson(ProductCategory category) {
		this.id = category.getId();
		this.name = category.getName();
		this.categories = category.getCategories().stream()
				.map(m -> new ProductCategoryJson(m))
				.collect(Collectors.toList());
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

	public List<ProductCategoryJson> getCategories() {
		return categories;
	}

	public void setCategories(List<ProductCategoryJson> categories) {
		this.categories = categories;
	}
}
