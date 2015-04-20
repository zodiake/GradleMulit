package com.sj.model.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Created by yagamai on 15-4-17.
 */
@Entity
@PrimaryKeyJoinColumn
public class Manufacturer extends SiteUser {

	@OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
	private Set<Product> products;
}
