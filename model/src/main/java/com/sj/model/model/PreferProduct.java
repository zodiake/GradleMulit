package com.sj.model.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prefer_products")
public class PreferProduct {
	@Embeddable
	public static class Id implements Serializable {

		@Column(name = "user_id")
		private Integer userId;

		@Column(name = "product_id")
		private Integer productId;

		public Id() {
		}

		public Id(Integer userId, Integer productId) {
			this.userId = userId;
			this.productId = productId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((productId == null) ? 0 : productId.hashCode());
			result = prime * result
					+ ((userId == null) ? 0 : userId.hashCode());
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
			Id other = (Id) obj;
			if (productId == null) {
				if (other.productId != null)
					return false;
			} else if (!productId.equals(other.productId))
				return false;
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			return true;
		}
	}

	@EmbeddedId
	private Id id = new Id();

	@Column(name = "ADDED_ON")
	private Calendar dateAdded;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private SiteUser user;

	@ManyToOne
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;

	public PreferProduct() {
	}

	public PreferProduct(SiteUser user, Product product) {
		this.user = user;
		this.product = product;
		this.id.productId = product.getId();
		this.id.userId = user.getId();

	}

	public Calendar getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Calendar dateAdded) {
		this.dateAdded = dateAdded;
	}

	public SiteUser getUser() {
		return user;
	}

	public void setUser(SiteUser user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
