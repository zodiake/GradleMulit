package com.sj.model.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sj.model.type.ScrollImageType;

@Entity
@Table(name = "scroll_image")
public class ScrollImage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "image_url")
	private String imageUrl;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "scroll_type")
	private ScrollImageType scrollType;

	@Column(name = "sort_number")
	private Integer sortNumber;
	
	public ScrollImage() {
		super();
	}

	public ScrollImage(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	public ScrollImageType getScrollType() {
		return scrollType;
	}

	public void setScrollType(ScrollImageType scrollType) {
		this.scrollType = scrollType;
	}

	public Integer getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
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
		ScrollImage other = (ScrollImage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}