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
public class CommonUser extends SiteUser {
	public CommonUser(){}
	
	public CommonUser(int id){
		super(id);
	}

	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	private Set<PreferProduct> preferProducts;

	public Set<PreferProduct> getPreferProducts() {
		return preferProducts;
	}

	public void setPreferProducts(Set<PreferProduct> preferProducts) {
		this.preferProducts = preferProducts;
	}
}
