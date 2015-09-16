package com.sj.repository.model;

import java.util.Calendar;

import com.sj.model.model.Product;

public class ProductJson {
	private Long id;
	private String name;
	private Long firstCategory;
	private String firstCategoryName;
	private Long secondCategory;
	private String secondCategoryName;
	private float price;
	private Calendar createdTime;
	private String stateName;

	public ProductJson(Product p) {
		this.id = p.getId();
		this.name = p.getName();
		this.firstCategory = p.getFirstCategory().getId();
		this.firstCategoryName = p.getFirstCategory().getName();
		this.secondCategory = p.getSecondCategory().getId();
		this.secondCategoryName = p.getSecondCategory().getName();
		this.price = p.getPrice();
		this.createdTime = p.getCreatedTime();
		this.stateName = p.getStatus().toString();
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getFirstCategoryName() {
		return firstCategoryName;
	}

	public void setFirstCategoryName(String firstCategoryName) {
		this.firstCategoryName = firstCategoryName;
	}

	public String getSecondCategoryName() {
		return secondCategoryName;
	}

	public void setSecondCategoryName(String secondCategoryName) {
		this.secondCategoryName = secondCategoryName;
	}

	public void setFirstCategory(Long firstCategory) {
		this.firstCategory = firstCategory;
	}

	public void setSecondCategory(Long secondCategory) {
		this.secondCategory = secondCategory;
	}

	public Long getFirstCategory() {
		return firstCategory;
	}

	public Long getSecondCategory() {
		return secondCategory;
	}
}
