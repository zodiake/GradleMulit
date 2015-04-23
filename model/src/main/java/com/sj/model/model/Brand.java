package com.sj.model.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sj.model.type.ActivateEnum;

@Entity
@Table(name = "brand")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@Enumerated
	private ActivateEnum acitvate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	private Calendar craetedTime;

	@OneToOne(mappedBy = "brand")
	private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ActivateEnum getAcitvate() {
		return acitvate;
	}

	public void setAcitvate(ActivateEnum acitvate) {
		this.acitvate = acitvate;
	}

	public Calendar getCraetedTime() {
		return craetedTime;
	}

	public void setCraetedTime(Calendar craetedTime) {
		this.craetedTime = craetedTime;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Brand other = (Brand) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
