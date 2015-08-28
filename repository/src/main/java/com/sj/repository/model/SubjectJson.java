package com.sj.repository.model;

import java.util.Calendar;

import com.sj.model.model.Subject;

public class SubjectJson {
	private Long id;
	private String name;
	private String img;
	private Calendar createdTime;

	public SubjectJson(Subject s) {
		this.id = s.getId();
		this.name = s.getName();
		this.img = s.getImage();
		this.createdTime = s.getCreatedTime();
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}
}
