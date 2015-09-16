package com.sj.model.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("sc")
public class SubjectCategory extends Category {
	@OneToMany(mappedBy = "category")
	private List<Subject> subjects;

	public SubjectCategory() {
	}

	public SubjectCategory(Long id) {
		this.id = id;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
}