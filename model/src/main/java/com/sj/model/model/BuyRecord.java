package com.sj.model.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "buy_record")
public class BuyRecord implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "no_id")
	private String noId;

	@NotBlank(message = "请输入项目名称")
	private String name;

	@Column(name = "fund_category")
	@NotBlank(message = "请输入经费类别")
	private String fundCategory; 
	
	@NotBlank(message = "请输入申请理由")
	private String reason; 

	@JoinColumn(name = "user_id")
	@ManyToOne
	private CommonUser user;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Calendar createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arrival_time")
	@NotNull(message="请选择到货时间")
	private Calendar arrivalTime;

	private float price;

	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE},fetch =FetchType.EAGER)
	@JoinColumn(name="buy_id",insertable = false,updatable = false)
	private Set<BuyProduct> products;

	public BuyRecord() {
	}

	public BuyRecord(Long id) {
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

	public String getFundCategory() {
		return fundCategory;
	}

	public void setFundCategory(String fundCategory) {
		this.fundCategory = fundCategory;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public Calendar getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Calendar arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Set<BuyProduct> getProducts() {
		return products;
	}

	public void setProducts(Set<BuyProduct> products) {
		this.products = products;
	}

	public String getNoId() {
		return noId;
	}

	public void setNoId(String noId) {
		this.noId = noId;
	}

	public CommonUser getUser() {
		return user;
	}

	public void setUser(CommonUser user) {
		this.user = user;
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
		BuyRecord other = (BuyRecord) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
