package com.sj.repository.util;

import javax.validation.constraints.Size;

public class MobileVerificationForm {
	@Size(min=11,max=11,message="请输入正确的手机号码")
	private String phone;
	private String code;
	public MobileVerificationForm(){
		
	}

	public MobileVerificationForm(String phone){
		this.phone = phone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
