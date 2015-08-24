package com.sj.admin.util;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ProductStatusEnum;

public class AdminSeachProductForm {
	private ProductCategory type;
	private ProductCategory first;
	private ProductCategory second;
	private ProductStatusEnum status;
	
	public ProductCategory getType() {
		return type;
	}
	public void setType(ProductCategory type) {
		this.type = type;
	}
	public ProductCategory getFirst() {
		return first;
	}
	public void setFirst(ProductCategory first) {
		this.first = first;
	}
	public ProductCategory getSecond() {
		return second;
	}
	public void setSecond(ProductCategory second) {
		this.second = second;
	}
	public ProductStatusEnum getStatus() {
		return status;
	}
	public void setStatus(ProductStatusEnum status) {
		this.status = status;
	}

}
