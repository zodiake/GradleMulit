package com.sj.repository.search.model;

import java.util.Calendar;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.sj.model.model.Information;

@Document(indexName = "sj", type = "info")
public class InfoSearch {
	@Id
	@Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
	private Long id;

	private String title;

	@Field(type = FieldType.Date)
	private Calendar createdTime;

	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String summary;

	public InfoSearch() {
	}

	public InfoSearch(Information info) {
		this.id = info.getId();
		this.title = info.getTitle();
		this.createdTime = info.getCreatedTime();
		this.summary = info.getSummary();
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

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
