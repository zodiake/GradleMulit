package com.sj.model.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("sc")
public class SubjectCategory extends Category {

	public SubjectCategory() {
	}

	public SubjectCategory(Long id) {
		this.id = id;
	}

}