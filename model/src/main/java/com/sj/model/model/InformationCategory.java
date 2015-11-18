package com.sj.model.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("ic")
public class InformationCategory extends Category {

	@Column(name = "url")
	private String url;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private InformationCategory parent;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Information> informations;
	
	public InformationCategory() {
		super();
	}
	
	public InformationCategory(Long id) {
		super();
		this.id = id;
	}
	
	public InformationCategory(String name) {
		this.name = name;
	}

	public InformationCategory getParent() {
		return parent;
	}

	public void setParent(InformationCategory parent) {
		this.parent = parent;
	}

	public void setInformations(Set<Information> informations) {
		this.informations = informations;
	}

	public Set<Information> getInformations() {
		return informations;
	}

	public void setAdvertisements(Set<Information> informations) {
		this.informations = informations;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
