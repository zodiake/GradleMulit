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
import javax.persistence.Transient;

import com.sj.model.type.TitleEnum;

/**
 * Created by yagamai on 15-4-17.
 */
@Entity
@Table(name = "common_user")
@PrimaryKeyJoinColumn
public class CommonUser extends SiteUser {
	private String company; // 单位*

	private String department; // 部门

	@Column(name = "title")
	private TitleEnum title; // 职位*

	@Column(name = "company_phone")
	private String companyPhone; // 公司电话*

	private String fax; // 传真

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
	private Province province; // 省份*

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city; // 城市*

	private String address; // 详细地址*

	private String code; // 邮编*

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "info_id")
	private UserIndustryInfo industryInfo; // 行业信息

	@Transient
	private String captcha;

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

	public TitleEnum getTitle() {
		return title;
	}

	public void setTitle(TitleEnum title) {
		this.title = title;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
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
}
