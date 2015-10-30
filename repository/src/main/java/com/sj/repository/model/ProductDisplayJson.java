package com.sj.repository.model;

import java.util.Calendar;

import com.sj.model.model.ProductDisplay;
import com.sj.model.type.ActivateEnum;

public class ProductDisplayJson {
	private Long id;
	private Long productId;
	private String productName;
	private ActivateEnum state;
	private Calendar createdTime;
	
	public ProductDisplayJson(ProductDisplay display){
		this.id = display.getId();
		this.productId = display.getProduct().getId();
		this.productName = display.getProduct().getName();
		this.state = display.getState();
		this.createdTime = display.getCreatedTime();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ActivateEnum getState() {
		return state;
	}
	public void setState(ActivateEnum state) {
		this.state = state;
	}
	public Calendar getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}
}
