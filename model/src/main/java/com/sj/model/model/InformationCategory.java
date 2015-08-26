package com.sj.model.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@DiscriminatorValue("ic")
public class InformationCategory extends Category implements Serializable {


	@Column(name = "url")
	private String url;

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
