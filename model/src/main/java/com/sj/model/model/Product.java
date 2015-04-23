package com.sj.model.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	private String description;

	@Column(name = "cover_img")
	private String coverImg;

	private float price;

	@OneToMany(mappedBy = "product")
	private Set<ProductSubject> productSubjects;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	private String url;

	@OneToOne
	@JoinColumn(name="brand_id")
	private Brand brand;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creted_by")
	private Provider createdBy;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Set<ProductSubject> getProductSubjects() {
		return productSubjects;
	}

	public void setProductSubjects(Set<ProductSubject> productSubjects) {
		this.productSubjects = productSubjects;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Provider getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Provider createdBy) {
		this.createdBy = createdBy;
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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
