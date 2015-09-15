package com.sj.repository.model;

import java.util.List;
import java.util.stream.Collectors;

import com.sj.model.model.Solution;
import com.sj.model.type.ActivateEnum;

public class SolutionDetailJson {
	private Long id;
	private String name;
	private List<ProductJson> products;
	private ActivateEnum active;

	public SolutionDetailJson(Solution s) {
		this.id = s.getId();
		this.name = s.getName();
		this.products = s.getProducts().stream().map(c -> new ProductJson(c))
				.collect(Collectors.toList());
		this.active = s.getActive();
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

	public List<ProductJson> getProducts() {
		return products;
	}

	public void setProducts(List<ProductJson> products) {
		this.products = products;
	}

}
