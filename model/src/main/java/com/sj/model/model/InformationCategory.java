package com.sj.model.model;

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
public class InformationCategory extends Category {
	
	public static final String ZX="资讯";
	public static final String HYYW="HYYW";
	public static final String XPCG="XPCG";
	public static final String CSDT="CSDT";
	 
	public static final InformationCategory getFirst(String url){
		System.out.println("url="+url);
		if(url == HYYW)
			return new InformationCategory(7l);
		if(url == XPCG)
			return new InformationCategory(8l);
		if(url == CSDT)
			return new InformationCategory(9l);
		return null;
	}
	
	@Column(name="url")
	private String url;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Information> informations;
	
	public InformationCategory(String name){
		this.name=name;
	}

	public Set<Information> getInformations() {
		return informations;
	}
	
	public void setAdvertisements(Set<Information> informations) {
		this.informations = informations;
	}

	public InformationCategory() {
		super();
	}

	public InformationCategory(Long id) {
		super();
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
