package com.sj.repository.model;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.sj.model.model.Subject;
import com.sj.model.type.ActivateEnum;

public class SubjectDetailJson {
	private Long id;
	private String name;
	private String cover;
	private Calendar createdTime;
	private Calendar updatedTime;
	private String content;
	private String createdBy;
	private List<SolutionDetailJson> solutions;
	private String summary;
	private Long category;
	private ActivateEnum active;
	private ActivateEnum showOnIndex;

	public SubjectDetailJson(Subject s) {
		this.id = s.getId();
		this.name = s.getName();
		this.cover = s.getImage();
		this.createdTime = s.getCreatedTime();
		this.updatedTime = s.getUpdatedTime();
		this.content = s.getContent().getContent();
		this.solutions = s.getSolutions().stream()
				.map(c -> new SolutionDetailJson(c))
				.collect(Collectors.toList());
		this.summary = s.getSummary();
		this.category = s.getCategory().getId();
		this.active = s.getActivate();
		this.showOnIndex = s.getShowOnIndex();
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

	public List<SolutionDetailJson> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<SolutionDetailJson> solutions) {
		this.solutions = solutions;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public ActivateEnum getShowOnIndex() {
		return showOnIndex;
	}

	public void setShowOnIndex(ActivateEnum showOnIndex) {
		this.showOnIndex = showOnIndex;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreateBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
