package com.sj.model.model;


import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@DiscriminatorValue("ac")
public class AdvertismentCategory extends Category {
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Advertisement> advs;

	public AdvertismentCategory() {
		super();
	}

	public AdvertismentCategory(Long id) {
		super();
		this.id = id;
	}

	public Set<Advertisement> getAdvs() {
		return advs;
	}

	public void setAdvs(Set<Advertisement> advs) {
		this.advs = advs;
	}
}
