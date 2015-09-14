package com.sj.repository.search.model;

import java.util.Calendar;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.sj.model.model.Subject;

public class SubjectSearch {
	@Id
	@Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
	private Long id;

	@Field(type = FieldType.String)
	private String title;

	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String summary;

	private String image;

	@Field(type = FieldType.Date)
	private Calendar createdTime;

	public SubjectSearch() {

	}

	public SubjectSearch(Subject subject) {
		this.id = subject.getId();
		this.title = subject.getName();
		this.image = subject.getImage();
		this.createdTime = subject.getCreatedTime();
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
