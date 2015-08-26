package com.sj.repository.model;

import com.sj.model.model.Brand;

public class BrandJson {
	private Long id;
	private String title;
	private String cover;
	private String state;

	public BrandJson(Brand b) {
		this.id = b.getId();
		this.title = b.getName();
		this.cover = b.getCoverImg();
		this.state = b.getActivate().toString();
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

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
