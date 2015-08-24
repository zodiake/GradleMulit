package com.sj.model.model;

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

@Entity
@Table(name = "buy_record")
public class BuyRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "no_id")
	private String noId;
	
	private String name;
	
	@Column(name="fund_category")
	private String fundCategory;		//经费类别
	
	private String reason;				//申请理由
	
	@JoinColumn(name = "user_id")
	@ManyToOne
	private CommonUser user;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Calendar createTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arrival_time")
	private Calendar arrivalTime;
	
	private float price;
	
	@OneToMany( mappedBy = "buy",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<BuyProduct> products;
	
	public BuyRecord(){
		
	}
	
	public BuyRecord(Long id){
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
}