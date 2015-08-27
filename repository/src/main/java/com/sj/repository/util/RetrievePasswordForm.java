package com.sj.repository.util;

import javax.validation.constraints.Size;

public class RetrievePasswordForm {

	private String phone;
	@Size(min=6,message="密码最少为6位")
	private String password;
	private String confirm;

	public RetrievePasswordForm() {

	}

	public RetrievePasswordForm(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
