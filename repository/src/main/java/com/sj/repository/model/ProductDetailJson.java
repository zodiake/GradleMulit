package com.sj.repository.model;

import java.util.Calendar;

import com.sj.model.model.Product;

public class ProductDetailJson {
	private Long id;
	private Calendar createdTime;
	private String firstCategoryName;
	private Long firstCategory;
	private String state;
	private Calendar authenticatedTime;
	private String secondCategoryName;
	private Long secondCategory;
	private String name;
	private float price;
	private String original;
	private String specifications;
	private String tag;
	private String content;
	private String modal;

	public ProductDetailJson(Product p) {
		this.id = p.getId();
		this.createdTime = p.getCreatedTime();
		this.firstCategoryName = p.getFirstCategory().getName();
		this.firstCategory = p.getFirstCategory().getId();
		this.state = p.getStatus().toString();
		this.authenticatedTime = p.getAuthenticatedTime();
		this.secondCategoryName = p.getSecondCategory().getName();
		this.secondCategory = p.getSecondCategory().getId();
		this.name = p.getName();
		this.price = p.getPrice();
		this.original = p.getPlaceOfProduction().toString();
		this.specifications = p.getSpecifications();
		this.tag = p.getLabel();
		this.modal = p.getModel();
		this.content = p.getContent().getContent();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	public String getFirstCategoryName() {
		return firstCategoryName;
	}

	public void setFirstCategoryName(String firstCategoryName) {
		this.firstCategoryName = firstCategoryName;
	}

	public Long getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(Long firstCategory) {
		this.firstCategory = firstCategory;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Calendar getAuthenticatedTime() {
		return authenticatedTime;
	}

	public void setAuthenticatedTime(Calendar authenticatedTime) {
		this.authenticatedTime = authenticatedTime;
	}

	public String getSecondCategoryName() {
		return secondCategoryName;
	}

	public void setSecondCategoryName(String secondCategoryName) {
		this.secondCategoryName = secondCategoryName;
	}

	public Long getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(Long secondCategory) {
		this.secondCategory = secondCategory;
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

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getModal() {
		return modal;
	}

	public void setModal(String modal) {
		this.modal = modal;
	}
}
