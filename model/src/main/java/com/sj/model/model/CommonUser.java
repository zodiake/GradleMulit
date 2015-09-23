package com.sj.model.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by yagamai on 15-4-17.
 */
@Entity
@Table(name = "common_user")
@PrimaryKeyJoinColumn
public class CommonUser extends SiteUser {
	@NotBlank(message = "单位不能为空")
	private String company; // 单位*
	@NotBlank(message = "部门不能为空")
	private String department; // 部门
	
	@NotBlank(message = "联系人职务不能为空")
	private String position; // 联系人职务*

	@Column(name = "company_phone")
	@NotBlank(message = "企业电话不能为空")
	private String companyPhone; // 企业电话*

	private String fax; // 传真

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
	private Province province; // 省份*

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	@NotNull(message = "请选择省份/城市")
	private City city; // 城市*

	@NotBlank(message = "详细地址不能为空")
	private String address; // 详细地址*

	@Pattern(regexp = "[0-9]{6}", message = "邮编为6位数组")
	private String code; // 邮编*

	@OneToOne()
	@JoinColumn(name = "info_id")
	@NotNull(message = "请选择行业信息")
	private UserIndustryInfo industryInfo; // 行业信息

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<PreferProduct> preferProducts;

	public CommonUser() {
	}

	public CommonUser(Long id) {
		super(id);
	}

	public Set<PreferProduct> getPreferProducts() {
		return preferProducts;
	}

	public void setPreferProducts(Set<PreferProduct> preferProducts) {
		this.preferProducts = preferProducts;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Province getProvince() {
		return province;
	}

	public UserIndustryInfo getIndustryInfo() {
		return industryInfo;
	}

	public void setIndustryInfo(UserIndustryInfo industryInfo) {
		this.industryInfo = industryInfo;
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
		CommonUser other = (CommonUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
