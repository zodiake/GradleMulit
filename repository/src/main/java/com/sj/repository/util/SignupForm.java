package com.sj.repository.util;

import javax.validation.constraints.Size;

import com.sj.model.model.CommonUser;


public class SignupForm extends CommonUser{
	@Size(min = 4, max = 10)
	private String name;
	private String password;
	private String confirm;
	private String captcha;

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
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


}
