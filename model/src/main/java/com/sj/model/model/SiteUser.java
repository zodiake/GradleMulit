package com.sj.model.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.sj.model.type.ActivateEnum;
import com.sj.model.type.SexEnum;

@Entity
@Table(name = "site_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class SiteUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

//	@Size(min=6,max = 20, message = "用户名长度最长为20位")
	@NotNull(message = "用户名不能为空")
	private String name; // 用户名
	
	@Column(name="real_name")
	private String realName;
	
	@Column(name="sex")
	private SexEnum sex;
	
	@NotNull(message = "密码不能为空")
	private String password; // 密码
	
	@Column(name = "site_Authority")
	private String siteAuthority; // 网站权限

	@Enumerated
	private ActivateEnum enabled; // 是否激活

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	private Calendar createdTime; // 创建时间

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "authenticated_time")
	private Calendar authenticatedTime; // 鉴定时间

	// new
//	@NotNull(message="邮箱不能为空")
//	@Email(message="请输入正确的邮箱地址")
	private String email; // 邮箱
	
//	@NotNull(message = "手机号码不能为空")
//	@Size(min=11,max=11,message = "手机号码只能为11位")
	private String phone; // 手机号码
	
	@Transient
	private String error;

	public SiteUser() {
	}

	public SiteUser(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ActivateEnum getEnabled() {
		return enabled;
	}

	public void setEnabled(ActivateEnum enabled) {
		this.enabled = enabled;
	}

	public String getSiteAuthority() {
		return siteAuthority;
	}

	public void setSiteAuthority(String siteAuthority) {
		this.siteAuthority = siteAuthority;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	public Calendar getAuthenticatedTime() {
		return authenticatedTime;
	}

	public void setAuthenticatedTime(Calendar authenticatedTime) {
		this.authenticatedTime = authenticatedTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SiteUser other = (SiteUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public SexEnum getSex() {
		return sex;
	}

	public void setSex(SexEnum sex) {
		this.sex = sex;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
