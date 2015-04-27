package com.sj.model.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Column(name = "cover_img")
	private String coverImg;

	private float price;

	@OneToMany(mappedBy = "product")
	private Set<ProductSubject> productSubjects;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "first_category_id")
	private Category firstCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "second_category_id")
	private Category secondCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "third_category_id")
	private Category thirdCategory;

	private String url;

	@OneToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	private Provider createdBy;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "content_id")
	private Content content;

	public Product() {
		super();
	}

	public Product(Long id) {
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

	public Category getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(Category firstCategory) {
		this.firstCategory = firstCategory;
	}

	public Category getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(Category secondCategory) {
		this.secondCategory = secondCategory;
	}

	public Category getThirdCategory() {
		return thirdCategory;
	}

	public void setThirdCategory(Category thirdCategory) {
		this.thirdCategory = thirdCategory;
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
	
	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
