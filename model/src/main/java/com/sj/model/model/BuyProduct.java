package com.sj.model.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "buy_product")
public class BuyProduct {
	@Embeddable
	public static class Id implements Serializable {

		@Column(name = "buy_id")
		private Integer buyId;

		@Column(name = "product_id")
		private Integer productId;

		public Id() {
		}

		public Id(Integer buyId, Integer productId) {
			this.buyId = buyId;
			this.productId = productId;
		}
	}

	@EmbeddedId
	private Id id = new Id();

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "buy_id",insertable = false, updatable = false)
	private BuyRecord buy;

	@ManyToOne()
	@JoinColumn(name = "product_id",insertable = false, updatable = false)
	private Product product;
	
	private Integer number;

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

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
