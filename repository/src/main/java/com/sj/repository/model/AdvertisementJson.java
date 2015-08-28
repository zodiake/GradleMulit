package com.sj.repository.model;

import java.util.Calendar;

import com.sj.model.model.Advertisement;

public class AdvertisementJson {
	private Long id;
	private String categoryName;
	private Long category;
	private String cover;
	private String url;
	private String state;
	private Calendar createdTime;
	private Calendar updatedTime;

	public AdvertisementJson(Advertisement advertisement) {
		this.id = advertisement.getId();
		this.categoryName = advertisement.getCategory().getName();
		this.cover = advertisement.getCoverImg();
		this.category = advertisement.getCategory().getId();
		this.url = advertisement.getUrl();
		this.state = advertisement.getActivate().toString();
		this.createdTime = advertisement.getCreatedTime();
		this.updatedTime = advertisement.getUpdatedTime();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	public Calendar getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Calendar updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}
}
