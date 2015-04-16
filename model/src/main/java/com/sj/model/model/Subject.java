package com.sj.model.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sj.model.type.ActivateEnum;

@Entity
@Table(name = "subject")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	@Enumerated
	private ActivateEnum showOnIndex;

	@Enumerated
	private ActivateEnum activate;

	@ManyToMany(mappedBy = "subjects")
	private Set<Product> products;
}
