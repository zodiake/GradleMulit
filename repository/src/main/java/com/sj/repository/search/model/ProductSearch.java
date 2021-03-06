package com.sj.repository.search.model;

import java.util.Calendar;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.sj.model.model.Product;
import com.sj.model.type.PlaceEnum;

@Document(indexName = "sj", type = "product")
public class ProductSearch {
	@Id
	@Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
	private Long id;

	// 二级分类
	@Field(type = FieldType.Long)
	private Long secondCategory;

	// 三级分类
	@Field(type = FieldType.Long)
	private Long thirdCategory;

	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String secondCategoryName;

	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String thirdCategoryName;

	// 价格
	@Field(type = FieldType.Double)
	private Float price;

	// 品牌
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String brand;

	// 封面图片
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String imgurl;

	// 标题
	@Field(type = FieldType.String)
	private String title;

	// 商品url
	private String url;

	// 评论数
	@Field(type = FieldType.Integer)
	private int review;

	@Field(type = FieldType.Integer)
	private int original;

	@Field(type = FieldType.String)
	private String tag;

	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String model;

	@Field(type = FieldType.Date, index = FieldIndex.not_analyzed)
	private Calendar createdTime;

	public ProductSearch() {
	}

	public ProductSearch(Product i) {
		this.id = i.getId();
		this.brand = i.getBrand().getName();
		this.title = i.getName();
		this.secondCategory = i.getSecondCategory().getId();
		this.secondCategoryName = i.getSecondCategory().getName();
		this.thirdCategory = i.getThirdCategory().getId();
		this.thirdCategoryName = i.getThirdCategory().getName();
		this.price = i.getPrice();
		this.imgurl = i.getCoverImg();
		this.url = i.getUrl();
		this.original = i.getPlaceOfProduction().equals(PlaceEnum.DOMESTIC) ? 1
				: 0;
		this.tag = i.getLabel();
		this.model = i.getModel();
		this.createdTime = i.getAuthenticatedTime();
		this.review = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOriginal() {
		return original;
	}

	public void setOriginal(int original) {
		this.original = original;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Long getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(Long secondCategory) {
		this.secondCategory = secondCategory;
	}

	public Long getThirdCategory() {
		return thirdCategory;
	}

	public void setThirdCategory(Long thirdCategory) {
		this.thirdCategory = thirdCategory;
	}

	public String getSecondCategoryName() {
		return secondCategoryName;
	}

	public void setSecondCategoryName(String secondCategoryName) {
		this.secondCategoryName = secondCategoryName;
	}

	public String getThirdCategoryName() {
		return thirdCategoryName;
	}

	public void setThirdCategoryName(String thirdCategoryName) {
		this.thirdCategoryName = thirdCategoryName;
	}

	public int getReview() {
		return review;
	}

	public void setReview(int review) {
		this.review = review;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
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
		ProductSearch other = (ProductSearch) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
