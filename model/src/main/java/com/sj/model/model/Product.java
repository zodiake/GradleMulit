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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.sj.model.type.OriginalEnum;
import com.sj.model.type.PlaceEnum;
import com.sj.model.type.ProductStatusEnum;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	// new
	private String nameEnglish; // 产品英文名

	private String model; // 型号

	private PlaceEnum placeOfProduction; // 产地

	private String specifications; // 规格

	private ProductStatusEnum status;

	private String label;
	// end

	@Column(name = "cover_img")
	private String coverImg;

	private float price;

	@ManyToMany(mappedBy = "products")
	private Set<Subject> subjects;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "first_category_id")
	private ProductCategory firstCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "second_category_id")
	private ProductCategory secondCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "third_category_id")
	private ProductCategory thirdCategory;

	private String url;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	private Provider createdBy;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST })
	@JoinColumn(name = "content_id")
	private Content content;

	@Transient
	private Long viewCount;

	@Transient
	private Long reviewCount = 0l;
	@Transient
	private Long collectionCount;

	@Transient
	private Long buyCount;

	@Transient
	private boolean isCollection;// 是否被收藏

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	private Calendar createdTime;

	private String serialNO;

	private OriginalEnum original;

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

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

	public ProductCategory getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(ProductCategory firstCategory) {
		this.firstCategory = firstCategory;
	}

	public ProductCategory getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(ProductCategory secondCategory) {
		this.secondCategory = secondCategory;
	}

	public ProductCategory getThirdCategory() {
		return thirdCategory;
	}

	public void setThirdCategory(ProductCategory thirdCategory) {
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

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	public String getSerialNO() {
		return serialNO;
	}

	public void setSerialNO(String serialNO) {
		this.serialNO = serialNO;
	}

	public OriginalEnum getOriginal() {
		return original;
	}

	public void setOriginal(OriginalEnum original) {
		this.original = original;
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

	public String getNameEnglish() {
		return nameEnglish;
	}

	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public PlaceEnum getPlaceOfProduction() {
		return placeOfProduction;
	}

	public void setPlaceOfProduction(PlaceEnum placeOfProduction) {
		this.placeOfProduction = placeOfProduction;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public ProductStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ProductStatusEnum status) {
		this.status = status;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public boolean isCollection() {
		return isCollection;
	}

	public void setCollection(boolean isCollection) {
		this.isCollection = isCollection;
	}

	public Long getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(Long reviewCount) {
		this.reviewCount = reviewCount;
	}

	public Long getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(Long collectionCount) {
		this.collectionCount = collectionCount;
	}

	public Long getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Long buyCount) {
		this.buyCount = buyCount;
	}

}