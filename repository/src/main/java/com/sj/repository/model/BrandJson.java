package com.sj.repository.model;

import java.util.Calendar;

import com.sj.model.model.Brand;

public class BrandJson {
	private Long id;
	private String title;
	private String cover;
	private String state;
	private String href;
	private String show;
	private Calendar showTime;

	public BrandJson(Brand b) {
		this.id = b.getId();
		this.title = b.getName();
		this.cover = b.getCoverImg();
		this.state = b.getActivate().toString();
		this.show = b.getShowOnIndex().toString();
		this.showTime = b.getShowTime();
		this.href = b.getHref();
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

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public Calendar getShowTime() {
		return showTime;
	}

	public void setShowTime(Calendar showTime) {
		this.showTime = showTime;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
}
