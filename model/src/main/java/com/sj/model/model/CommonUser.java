package com.sj.model.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sj.model.type.IndustryInformationEnum;
import com.sj.model.type.PositionEnum;
import com.sj.model.type.SexEnum;
import com.sj.model.type.TitleEnum;

/**
 * Created by yagamai on 15-4-17.
 */
@Entity
@PrimaryKeyJoinColumn
public class CommonUser extends SiteUser {
	public CommonUser() {
		
	}

	public CommonUser(Long id) {
		super(id);
	}


	// new
//	@NotNull(message = "真实姓名不能为空")
	@Column(name="real_name")
	private String realName; // 真实姓名*

//	@NotNull(message = "性别不能为空")
	private SexEnum sex; // 性别*

	// 详细信息
//	@NotNull(message = "单位不能为空")
	private String company; // 单位*

//	@NotNull(message = "部门不能为空")
	private String department; // 部门

//	@NotNull(message = "职位不能为空")
	@Column(name = "title")
	private TitleEnum title; // 职位*

//	@NotNull(message = "公司电话不能为空")
	@Column(name = "company_phone")
	private String companyPhone; // 公司电话*

	private String fax; // 传真

//	@NotNull(message = "请选择所在省份")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
	private Provider province; // 省份*

//	@NotNull(message = "请选择所在城市")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city; // 城市*

//	@NotNull(message = "详细地址不能为空")
	private String address; // 详细地址*

//	@NotNull(message = "邮编不能为空")
//	@Size(min = 6, max = 6, message = "邮编长度为6位")
	private String code; // 邮编*

	// 行业信息
//	@NotNull(message = "行业信息不能为空")
	@Column(name = "industry_information")
	private IndustryInformationEnum industryInformation; // 行业信息*

//	@NotNull(message = "职位信息不能为空")
	@Column(name = "position")
	private PositionEnum position; // 职位信息*
	// end

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<PreferProduct> preferProducts;
	
	@Transient
	private String captcha;

	public Set<PreferProduct> getPreferProducts() {
		return preferProducts;
	}

	public void setPreferProducts(Set<PreferProduct> preferProducts) {
		this.preferProducts = preferProducts;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public SexEnum getSex() {
		return sex;
	}

	public void setSex(SexEnum sex) {
		this.sex = sex;
	}

	public IndustryInformationEnum getIndustryInformation() {
		return industryInformation;
	}

	public void setIndustryInformation(
			IndustryInformationEnum industryInformation) {
		this.industryInformation = industryInformation;
	}

	public TitleEnum getTitle() {
		return title;
	}

	public void setTitle(TitleEnum title) {
		this.title = title;
	}

	public PositionEnum getPosition() {
		return position;
	}

	public void setPosition(PositionEnum position) {
		this.position = position;
	}

	public Provider getProvince() {
		return province;
	}

	public void setProvince(Provider province) {
		this.province = province;
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

}
