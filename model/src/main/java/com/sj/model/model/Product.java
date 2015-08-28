package com.sj.model.model;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.sj.model.type.PlaceEnum;
import com.sj.model.type.ProductStatusEnum;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "product")
@DiscriminatorColumn(name = "product_type")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@NotBlank(message = "商品名称不能为空")
	protected String name;
	protected String nameEnglish; // 产品英文名
	@NotBlank(message = "型号不能为空")
	protected String model; // 型号
	@NotNull(message = "请选择产地")
	protected PlaceEnum placeOfProduction; // 产地
	@NotBlank(message = "规格不能为空")
	protected String specifications; // 规格

	protected ProductStatusEnum status;
	@NotBlank(message = "商品标签不能为空")
	protected String label;
	@NotBlank(message = "请上传商品图片")
	@Column(name = "cover_img")
	protected String coverImg;

	@Pattern(regexp = "^[0-9]+\\.?[0-9]{0,2}$", message = "价格精确到小数点后一位")
	protected float price;

	@NotNull(message = "请选择一级目录")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "first_category_id")
	protected ProductCategory firstCategory;

	@NotNull(message = "请选择二级目录")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "second_category_id")
	protected ProductCategory secondCategory;
	@NotNull(message = "请选择三级目录")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "third_category_id")
	protected ProductCategory thirdCategory;

	protected String url;
	@NotNull(message = "请选择品牌")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	protected Brand brand;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	protected Provider createdBy;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST })
	@JoinColumn(name = "content_id")
	protected Content content;

	@Transient
	protected Long viewCount;

	@Transient
	private Long reviewCount = 0l;
	@Transient
	protected Long collectionCount;

	@Transient
	protected Long buyCount;

	@Transient
	protected boolean isCollection;// 是否被收藏

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	protected Calendar createdTime;

	protected String serialNO;

	@ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
	private List<Solution> solutions;

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
