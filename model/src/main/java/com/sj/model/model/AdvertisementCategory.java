package com.sj.model.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "advertisement_category")
@DiscriminatorValue("ac")
public class AdvertisementCategory extends Category {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Advertisement> advertisements;

	public Set<Advertisement> getAdvertisements() {
		return advertisements;
	}

	public void setAdvertisements(Set<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

	public AdvertisementCategory() {
	}

	public AdvertisementCategory(Long id) {
		this.id = id;
	}
}
