package com.sj.model.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.sj.model.type.ActivateEnum;
import com.sj.model.type.SexEnum;

@Entity
@Table(name = "site_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class SiteUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@NotBlank(message = "用户名不能为空")
	@Size(min=6,max=20,message="用户名长度为6-20位")
	protected String name; // 用户名

	@NotBlank(message = "真实姓名不能为空")
	@Column(name = "real_name")
	private String realName;

	@NotNull(message = "请选择性别")
	@Column(name = "sex")
	private SexEnum sex;

	@Size(min = 6, message = "密码最少为6位")
	private String password; // 密码

	@Column(name = "site_Authority")
	private String siteAuthority; // 网站权限

	@Enumerated
	private ActivateEnum enabled; // 是否激活

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Calendar createTime; // 创建时间

	@NotBlank(message = "邮箱不能为空")
	@Email(message = "请输入正确的邮箱")
	private String email; // 邮箱

	@Pattern(regexp = "[0-9]{11}", message = "手机号码为11位")
	private String phone; // 手机号码

	private int score;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<SiteRole> roles;
	
	@Transient
	private String captcha;//注册验证码

	public SiteUser() {
	}

	public SiteUser(Long id) {
		super();
		this.id = id;
	}

	public SiteUser(Long id, String name, String passwrod) {
		super();
		this.id = id;
		this.name = name;
		this.password = passwrod;
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

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<SiteRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SiteRole> roles) {
		this.roles = roles;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
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

}
