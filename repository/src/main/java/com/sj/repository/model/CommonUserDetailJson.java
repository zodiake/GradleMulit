package com.sj.repository.model;

import com.sj.model.model.CommonUser;
import com.sj.model.type.SexEnum;

public class CommonUserDetailJson {
	private String email;
	private String mobile;
	private String name;
	private String gender;
	private String company;
	private String department;
	private String phone;
	private String fax;
	private String province;
	private String city;
	private String address;
	private String code;
	private String industryInfo;
	private int score;

	public CommonUserDetailJson(CommonUser user) {
		this.email = user.getEmail();
		this.mobile = user.getPhone();
		this.name = user.getName();
		this.gender = user.getSex().equals(SexEnum.FEMALE) ? "男" : "女";
		this.company = user.getCompany();
		this.department = user.getDepartment();
		this.phone = user.getCompanyPhone();
		this.fax = user.getFax();
		if (user.getProvince() != null)
			this.province = user.getProvince().getName();
		this.city = user.getCity().getName();
		this.address = user.getAddress();
		this.code = user.getCode();
		if (user.getIndustryInfo() != null)
			this.industryInfo = user.getIndustryInfo().getName();
		this.score = user.getScore();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getIndustryInfo() {
		return industryInfo;
	}

	public void setIndustryInfo(String industryInfo) {
		this.industryInfo = industryInfo;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
