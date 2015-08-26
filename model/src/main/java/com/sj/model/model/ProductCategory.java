package com.sj.model.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@DiscriminatorValue("pc")
public class ProductCategory extends Category implements Serializable{
	
	@OneToMany(mappedBy = "parent",fetch = FetchType.LAZY)
	private Set<ProductCategory> categories;

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
	public ProductCategory(String name){
		super();
		this.name=name;
	}

	public Set<ProductCategory> getCategories() {
		return categories;
	}

	public void setCategories(Set<ProductCategory> categories) {
		this.categories = categories;
	}

	public ProductCategory getParent() {
		return parent;
	}

	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}
}
