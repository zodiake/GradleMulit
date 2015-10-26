package com.sj.model.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("sc")
public class SubjectCategory extends Category {
	
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	private List<SubjectCategory> categories;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private SubjectCategory parent;
	
	@OneToMany(mappedBy = "category")
	private List<Subject> subjects;

	public SubjectCategory() {
	}

	public SubjectCategory(Long id) {
		this.id = id;
	}

	
	public List<SubjectCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<SubjectCategory> categories) {
		this.categories = categories;
	}

	public SubjectCategory getParent() {
		return parent;
	}

	public void setParent(SubjectCategory parent) {
		this.parent = parent;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
}