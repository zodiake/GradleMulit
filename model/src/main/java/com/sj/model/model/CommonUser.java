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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.sj.model.type.TitleEnum;

/**
 * Created by yagamai on 15-4-17.
 */
@Entity
@Table(name = "common_user")
@PrimaryKeyJoinColumn
public class CommonUser extends SiteUser {
	@NotBlank(message="单位不能为空")
	private String company; // 单位*

	private String department; // 部门

	@Column(name = "company_phone")
	@Pattern(regexp="[0-9]{3,4}-[0-9]{8}",message = "请输入正确的公司电话，如：010-12345678")
	private String companyPhone; // 公司电话*
	
	private String fax; // 传真

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
	private Province province; // 省份*

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	@NotNull(message="请选择省份/城市")
	private City city; // 城市*

	@NotBlank(message="详细地址不能为空")
	private String address; // 详细地址*
	
	@Pattern(regexp="[0-9]{6}",message="邮编为6位数组")
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
