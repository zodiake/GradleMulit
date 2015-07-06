package com.sj.model.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

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
	public CommonUser(){}
	
	public CommonUser(Long id){
		super(id);
	}
	
	//new
	private String realName;			//真实姓名*
	private SexEnum sex;				//性别*
	//详细信息
	private String company;				//单位*
	private String department;			//部门
	@Column(name="title")
	private TitleEnum title;				//职位*
	@Column(name="company_phone")
	private String companyPhone;		//公司电话*
	private String fax;					//传真
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
	private Provider province; // 省份*

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city; // 城市*
	
	
	private String address;				//详细地址*
	private String code;				//邮编*
	
	//行业信息
	@Column(name = "industry_information")
	private IndustryInformationEnum industryInformation;		//行业信息*
	@Column(name = "position_information")
	private PositionEnum position;					//职位信息*
	//end

	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	private Set<PreferProduct> preferProducts;

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

	public void setIndustryInformation(IndustryInformationEnum industryInformation) {
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

}
