package com.sj.repository.model;

import java.text.SimpleDateFormat;

import com.sj.model.model.Provider;
import com.sj.model.type.SexEnum;

public class ProviderJson {
	private String name;
	private String gender;
	private String mobile;
	private String email;
	private String city;
	private String company;
	private String createTime;
	private int state;

	public ProviderJson(Provider user) {
		this.name = user.getName();
		this.gender = user.getSex().equals(SexEnum.MALE) ? "男" : "女";
		this.mobile = user.getPhone();
		this.email = user.getEmail();
		this.city = user.getCity().getName();
		this.company = user.getCompanyNameChina();
		this.state = user.getIsAuthenticated();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (user.getCreateTime() != null)
			this.createTime = format.format(user.getCreateTime().getTime());
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
