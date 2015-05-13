package com.sj.model.model;

import java.util.Calendar;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sj.model.type.ActivateEnum;

@Entity
@Table(name = "subject")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Enumerated
	@Column(name = "show_on_index")
	private ActivateEnum showOnIndex;

	@Enumerated
	private ActivateEnum activate;

	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	private Set<ProductSubject> productSubject;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Calendar createdTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private SubjectCategory category;

	public Subject() {
		super();
	}

	public Subject(Long id) {
		super();
		this.id = id;
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

	public ActivateEnum getShowOnIndex() {
		return showOnIndex;
	}

	public void setShowOnIndex(ActivateEnum showOnIndex) {
		this.showOnIndex = showOnIndex;
	}

	public ActivateEnum getActivate() {
		return activate;
	}

	public void setActivate(ActivateEnum activate) {
		this.activate = activate;
	}

	public Set<ProductSubject> getProductSubject() {
		return productSubject;
	}

	public void setProductSubject(Set<ProductSubject> productSubject) {
		this.productSubject = productSubject;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}
	
	public SubjectCategory getCategory() {
		return category;
	}

	public void setCategory(SubjectCategory category) {
		this.category = category;
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
		Subject other = (Subject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}