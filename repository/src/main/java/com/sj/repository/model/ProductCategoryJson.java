package com.sj.repository.model;

import java.io.Serializable;
import java.util.Calendar;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;

public class ProductCategoryJson implements Serializable {
	private Long id;
	private String name;
	private Long parent;
	private Calendar updatedTime;
	private ActivateEnum activate;

	public ProductCategoryJson(ProductCategory p) {
		this.id = p.getId();
		this.name = p.getName();
		if (p.getParent() != null)
			this.parent = p.getParent().getId();
		this.updatedTime = p.getUpdatedTime();
		this.activate = p.getActivate();
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

	public Calendar getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Calendar updatedTime) {
		this.updatedTime = updatedTime;
	}

	public ActivateEnum getActivate() {
		return activate;
	}

	public void setActivate(ActivateEnum activate) {
		this.activate = activate;
	}
}
