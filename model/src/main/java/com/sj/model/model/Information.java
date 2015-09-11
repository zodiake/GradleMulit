package com.sj.model.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.sj.model.type.ActivateEnum;

@Entity
@Table(name = "information")
public class Information implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "content_id")
	private InformationContent content;

	@Column(name = "create_by")
	private String createBy;

	@Enumerated
	private ActivateEnum activate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	private Calendar createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_time")
	private Calendar updatedTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private InformationCategory category;

	private String summary;

	@Transient
	private Long viewCount = 0l;

	public Information() {
	}

	public Information(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InformationContent getContent() {
		return content;
	}

	public void setContent(InformationContent content) {
		this.content = content;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	public InformationCategory getCategory() {
		return category;
	}

	public void setCategory(InformationCategory category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ActivateEnum getActivate() {
		return activate;
	}

	public void setActivate(ActivateEnum activate) {
		this.activate = activate;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Calendar getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Calendar updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Information other = (Information) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
