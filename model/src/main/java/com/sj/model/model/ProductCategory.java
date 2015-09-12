package com.sj.model.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("pc")
public class ProductCategory extends Category{

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	private List<ProductCategory> categories;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private ProductCategory parent;

	public ProductCategory() {
		super();
	}

	public ProductCategory(Long id) {
		super();
		this.id = id;
	}

	public ProductCategory(String name) {
		super();
		this.name = name;
	}

	public List<ProductCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<ProductCategory> categories) {
		this.categories = categories;
	}

	public ProductCategory getParent() {
		return parent;
	}

	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}

}
