package com.sj.model.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Deprecated
public class ProductSubject {
	@Embeddable
	public static class Id implements Serializable {

		@Column(name = "subject_id")
		private Integer subjectId;

		@Column(name = "product_id")
		private Integer productId;

		public Id() {
		}

		public Id(Integer subjectId, Integer productId) {
			this.subjectId = subjectId;
			this.productId = productId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((productId == null) ? 0 : productId.hashCode());
			result = prime * result
					+ ((subjectId == null) ? 0 : subjectId.hashCode());
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
			if (subjectId == null) {
				if (other.subjectId != null)
					return false;
			} else if (!subjectId.equals(other.subjectId))
				return false;
			return true;
		}
	}

	@EmbeddedId
	private Id id = new Id();

	@Column(name = "ADDED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateAdded;

	@Column(name = "sort_order")
	private Integer sortOrder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id", insertable = false, updatable = false)
	private Subject subject;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;

	public ProductSubject() {
	}

	public ProductSubject(Product product, Subject subject) {
		this.product = product;
		this.subject = subject;
	}

	public ProductSubject(Product product) {
		this.product = product;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public Calendar getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Calendar dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
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
		ProductSubject other = (ProductSubject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
