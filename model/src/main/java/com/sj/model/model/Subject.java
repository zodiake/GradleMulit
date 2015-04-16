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

	@Enumerated(EnumType.STRING)
	private ActivateEnum showOnIndex;

	@Enumerated(EnumType.STRING)
	private ActivateEnum active;

	@ManyToMany(mappedBy = "subjects")
	private Set<Product> products;
}
