package com.sj.repository.model;

import java.util.Calendar;

import com.sj.model.model.Information;

public class InformationJson {
	private Long id;
	private String title;
	private String categoryName;
	private Long category;
	private String state;
	private Calendar updatedTime;

	public InformationJson(Information info) {
		this.id = info.getId();
		this.title = info.getTitle();
		this.categoryName = info.getCategory().getName();
		this.category = info.getCategory().getId();
		this.state = info.getActivate().toString();
		this.updatedTime = info.getUpdatedTime();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Calendar getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Calendar updatedTime) {
		this.updatedTime = updatedTime;
	}
}
