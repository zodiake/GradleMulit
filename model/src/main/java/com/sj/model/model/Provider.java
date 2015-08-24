package com.sj.model.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sj.model.type.BusinessTypeEnum;
import com.sj.model.type.ComponyTypeEnum;
import com.sj.model.type.IndustryInformationEnum;
import com.sj.model.type.OutputEnum;
import com.sj.model.type.ScaleEnum;

/**
 * Created by yagamai on 15-4-17.
 */
@Entity
@Table(name = "provider")
@PrimaryKeyJoinColumn
public class Provider extends SiteUser {

	public Provider() {
	}

	public Provider(Long id) {
		super(id);
	}

	// new
	// 企业信息
	@Column(name = "company_name_china")
	private String companyNameChina; // 企业中文名*

	@Column(name = "company_name_english")
	private String companyNameEnglish; // 企业英文名

	@Column(name = "legal_person")
	private String legalPerson; // 法人

	@Column(name = "registered_capital")
	private String registeredCapital; // 注册资本

	@Column(name = "compony_type")
	private ComponyTypeEnum componyType; // 企业性质

	@Column(name = "main_product")
	private String mainProduct; // 主要产品*

	@Column(name = "business_type")
	private BusinessTypeEnum businessType;// 业务类型*

	@Column(name = "scale")
	private ScaleEnum scale; // 企业规模

	@Column(name = "output")
	private OutputEnum output; // 年产值

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST })
	@JoinColumn(name = "content_id")
	private Content content; // 企业介绍*

	@Column(name = "business_license_url")
	private String businessLicenseUrl; // 营业执照图片地址*

	@Column(name = "tax_registration_url")
	private String taxRegistrationUrl; // 税务登记图片地址*

	@Column(name = "structure_code_url")
	private String structureCodeUrl; // 组织结构图片地址*

	private String position; // 联系人职位*

	@Column(name = "provider_phone")
	private String providerPhone; // 联系人电话*

	private String fax; // 联系人传真*

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
	private Provider province; // 省份*

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city; // 城市*

	private String address; // 详细地址*

	private String code; // 邮编*

	private String website; // 公司网址

	@Transient
	private String captcha;

	// 行业信息
	@Column(name = "industry_information")
	private IndustryInformationEnum industryInformation;// 行业信息*

	public String getCompanyNameChina() {
		return companyNameChina;
	}

	public void setCompanyNameChina(String companyNameChina) {
		this.companyNameChina = companyNameChina;
	}

	public String getCompanyNameEnglish() {
		return companyNameEnglish;
	}

	public void setCompanyNameEnglish(String companyNameEnglish) {
		this.companyNameEnglish = companyNameEnglish;
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

	public String getMainProduct() {
		return mainProduct;
	}

	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}

	public BusinessTypeEnum getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessTypeEnum businessType) {
		this.businessType = businessType;
	}

	public ScaleEnum getScale() {
		return scale;
	}

	public void setScale(ScaleEnum scale) {
		this.scale = scale;
	}

	public OutputEnum getOutput() {
		return output;
	}

	public void setOutput(OutputEnum output) {
		this.output = output;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public String getBusinessLicenseUrl() {
		return businessLicenseUrl;
	}

	public void setBusinessLicenseUrl(String businessLicenseUrl) {
		this.businessLicenseUrl = businessLicenseUrl;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getProviderPhone() {
		return providerPhone;
	}

	public void setProviderPhone(String providerPhone) {
		this.providerPhone = providerPhone;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public ComponyTypeEnum getComponyType() {
		return componyType;
	}

	public void setComponyType(ComponyTypeEnum componyType) {
		this.componyType = componyType;
	}

	public IndustryInformationEnum getIndustryInformation() {
		return industryInformation;
	}

	public void setIndustryInformation(
			IndustryInformationEnum industryInformation) {
		this.industryInformation = industryInformation;
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
