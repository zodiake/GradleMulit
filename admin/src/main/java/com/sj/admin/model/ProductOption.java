package com.sj.admin.model;

import com.sj.model.type.ActivateEnum;

public class ProductOption {
	private String productType;
	private String firstCategory;
	private String secondCategory;
	private ActivateEnum state;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(String firstCategory) {
		this.firstCategory = firstCategory;
	}

	public String getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(String secondCategory) {
		this.secondCategory = secondCategory;
	}

	public ActivateEnum getState() {
		return state;
	}

	public void setState(ActivateEnum state) {
		this.state = state;
	}
}
