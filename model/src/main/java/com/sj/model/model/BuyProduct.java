package com.sj.model.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "buy_product")
public class BuyProduct implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "buy_id",insertable= true,updatable=true)
	private BuyRecord buy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id",insertable= true,updatable=true)
	private Product product;
	
	private Integer number;
	
	public BuyProduct(){
	}
	public BuyProduct(Long id){
		this.id = id;
	}
	public BuyProduct(BuyRecord buy,Product product,Integer number){
		this.product = product;
		this.buy = buy;
		this.number = number;
	}

	public BuyRecord getBuy() {
		return buy;
	}

	public void setBuy(BuyRecord buy) {
		this.buy = buy;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
