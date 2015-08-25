package com.sj.repository.model;

import com.sj.model.model.Provider;
import com.sj.model.type.SexEnum;

public class ProviderDetailJson {
	private String name;
	private String gender;
	private String email;
	private String mobile;
	private String position;
	private String phone;
	private String fax;
	private String province;
	private String city;
	private String code;
	private String address;
	private String companyUrl;
	private String chineseCompanyName;
	private String legalPerson;
	private String registeredCapital;
	private String componyType;
	private String focus;
	private String businessType;
	private String scale;
	private String output;
	private String content;
	private String businessLicenseUrl; // 营业执照图片地址*
	private String taxRegistrationUrl; // 税务登记图片地址*
	private String structureCodeUrl; // 组织结构图片地址*
	private String industryInfo;

	public ProviderDetailJson(Provider p) {
		this.name = p.getName();
		this.gender = p.getSex().equals(SexEnum.FEMALE) ? "女" : "男";
		this.email = p.getEmail();
		this.mobile = p.getProviderPhone();
		this.position = p.getPosition();
		this.phone = p.getPhone();
		this.fax = p.getFax();
		this.province = p.getProvince().getName();
		this.city = p.getCity().getName();
		this.code = p.getCode();
		this.address = p.getAddress();
		this.companyUrl = p.getWebsite();
		this.chineseCompanyName = p.getCompanyNameChina();
		this.legalPerson = p.getLegalPerson();
		this.registeredCapital = p.getRegisteredCapital();
		if (p.getComponyType() != null)
			this.componyType = p.getComponyType().toString();
		this.focus = p.getMainProduct();
		this.businessType = p.getBusinessType().toString();
		this.scale = p.getScale().toString();
		this.output = p.getOutput().toString();
		this.content = p.getContent();
		this.businessLicenseUrl = p.getBusinessLicenseUrl();
		this.taxRegistrationUrl = p.getTaxRegistrationUrl();
		this.structureCodeUrl = p.getStructureCodeUrl();
		if (p.getIndustryInfo() != null)
			this.industryInfo = p.getIndustryInfo().getName();
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public String getChineseCompanyName() {
		return chineseCompanyName;
	}

	public void setChineseCompanyName(String chineseCompanyName) {
		this.chineseCompanyName = chineseCompanyName;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getComponyType() {
		return componyType;
	}

	public void setComponyType(String componyType) {
		this.componyType = componyType;
	}

	public String getFocus() {
		return focus;
	}

	public void setFocus(String focus) {
		this.focus = focus;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBusinessLicenseUrl() {
		return businessLicenseUrl;
	}

	public void setBusinessLicenseUrl(String businessLicenseUrl) {
		this.businessLicenseUrl = businessLicenseUrl;
	}

	public String getTaxRegistrationUrl() {
		return taxRegistrationUrl;
	}

	public void setTaxRegistrationUrl(String taxRegistrationUrl) {
		this.taxRegistrationUrl = taxRegistrationUrl;
	}

	public String getStructureCodeUrl() {
		return structureCodeUrl;
	}

	public void setStructureCodeUrl(String structureCodeUrl) {
		this.structureCodeUrl = structureCodeUrl;
	}

	public String getIndustryInfo() {
		return industryInfo;
	}

	public void setIndustryInfo(String industryInfo) {
		this.industryInfo = industryInfo;
	}
}
