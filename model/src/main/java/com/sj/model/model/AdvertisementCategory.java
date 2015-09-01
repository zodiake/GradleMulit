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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdvertisementCategory other = (AdvertisementCategory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}