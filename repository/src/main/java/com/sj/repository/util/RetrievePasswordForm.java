package com.sj.repository.util;

public class RetrievePasswordForm {
	public RetrievePasswordForm(){
		
	}

	public RetrievePasswordForm(String phone){
		this.phone = phone;
	}
	private String phone;
	private String password;
	private String confirm;
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
