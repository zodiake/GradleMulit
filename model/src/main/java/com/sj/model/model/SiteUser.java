package com.sj.model.model;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sj.model.type.ActivateEnum;

@Entity
@Table(name = "site_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class SiteUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	private String password;

	@Column(name = "site_Authority")
	private String siteAuthority;

	@Enumerated
	private ActivateEnum enabled;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Calendar createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="authenticated_time")
	private Calendar authenticatedTime;

	public SiteUser(){}

	public SiteUser(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ActivateEnum getEnabled() {
		return enabled;
	}

	public void setEnabled(ActivateEnum enabled) {
		this.enabled = enabled;
	}

	public String getSiteAuthority() {
		return siteAuthority;
	}

	public void setSiteAuthority(String siteAuthority) {
		this.siteAuthority = siteAuthority;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}
	
	public Calendar getAuthenticatedTime() {
		return authenticatedTime;
	}

	public void setAuthenticatedTime(Calendar authenticatedTime) {
		this.authenticatedTime = authenticatedTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		SiteUser other = (SiteUser) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
