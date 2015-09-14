package com.sj.repository.model;

import java.util.Calendar;

import com.sj.model.model.Subject;

public class SubjectJson {
	private Long id;
	private String name;
	private String cover;
	private Calendar createdTime;
	private Calendar updatedTime;

	public SubjectJson(Subject s) {
		this.id = s.getId();
		this.name = s.getName();
		this.cover = s.getImage();
		this.createdTime = s.getCreatedTime();
		this.updatedTime = s.getUpdatedTime();
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

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
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
}
