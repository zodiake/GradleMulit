package com.sj.model.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("ac")
public class AdvertisementCategory extends Category {
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Advertisement> advs;

	public AdvertisementCategory() {
	}

	public AdvertisementCategory(Long id) {
		this.id = id;
	}

	public Set<Advertisement> getAdvs() {
		return advs;
	}

	public void setAdvs(Set<Advertisement> advs) {
		this.advs = advs;
	}
}