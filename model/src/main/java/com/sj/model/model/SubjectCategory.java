package com.sj.model.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subject_category")
@DiscriminatorValue("sc")
public class SubjectCategory extends Category {

	public SubjectCategory() {
		super();
	}

	public SubjectCategory(Long id) {
		super();
		this.id = id;
	}

	@OneToMany
	private Set<Subject> subjects;

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

}
