package com.sj.admin.model;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ProductStatusEnum;

public class ProductOption {
	private String productType;
	private ProductCategory firstCategory;
	private ProductCategory secondCategory;
	private ProductStatusEnum state;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public ProductCategory getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(ProductCategory firstCategory) {
		this.firstCategory = firstCategory;
	}

	public ProductCategory getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(ProductCategory secondCategory) {
		this.secondCategory = secondCategory;
	}

	public ProductStatusEnum getState() {
		return state;
	}

	public void setState(ProductStatusEnum state) {
		this.state = state;
	}
}
