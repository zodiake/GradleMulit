package com.sj.repository.model;

import java.util.Calendar;

import com.sj.model.model.Subject;
import com.sj.model.type.ActivateEnum;

public class SubjectJson {
	private Long id;
	private String name;
	private String cover;
	private Calendar createdTime;
	private Calendar updatedTime;
	private Long category;
	private ActivateEnum active;
	private String categoryName;
	private String createdBy;

	public SubjectJson(Subject s) {
		this.id = s.getId();
		this.name = s.getName();
		this.cover = s.getImage();
		this.createdTime = s.getCreatedTime();
		this.updatedTime = s.getUpdatedTime();
		this.category = s.getCategory().getId();
		this.active = s.getActivate();
		this.categoryName = s.getCategory().getName();
		this.createdBy = s.getCreatedBy();
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

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public ActivateEnum getActive() {
		return active;
	}

	public void setActive(ActivateEnum active) {
		this.active = active;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreateBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
