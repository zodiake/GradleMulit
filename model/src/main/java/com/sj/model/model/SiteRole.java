package com.sj.model.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sj.model.type.ActivateEnum;

@Entity
@Table(name = "site_role")
public class SiteRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "role_name")
	private String roleName;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private List<SiteUser> user;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_menu", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
	private List<SiteMenu> menus;

	@Enumerated
	private ActivateEnum active;

	public SiteRole() {
	}

	public SiteRole(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<SiteUser> getUser() {
		return user;
	}

	public void setUser(List<SiteUser> user) {
		this.user = user;
	}

	public List<SiteMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<SiteMenu> menus) {
		this.menus = menus;
	}

	public ActivateEnum getActive() {
		return active;
	}

	public void setActive(ActivateEnum active) {
		this.active = active;
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
		SiteRole other = (SiteRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
