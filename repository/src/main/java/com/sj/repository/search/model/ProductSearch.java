package com.sj.repository.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.sj.model.model.Product;

@Document(indexName = "sj", type = "product")
public class ProductSearch {
	@Id
	private Long id;

	// 二级分类
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String secondCategory;

	// 三级分类
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String thirdCategory;

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
	@Field(type = FieldType.String, indexAnalyzer = "standardAnalyzer")
	private String title;

	// 类型 搜索通过类型区分
	private String type;

	// 产品编号
	private String serialNo;

	// 商品url
	private String url;

	private String original;

	public ProductSearch() {

	}

	public ProductSearch(Long id, String secondCategory, String thirdCategory,
			Float price, String brand, String imgurl, String title) {
		super();
		this.id = id;
		this.secondCategory = secondCategory;
		this.thirdCategory = thirdCategory;
		this.price = price;
		this.brand = brand;
		this.imgurl = imgurl;
		this.title = title;
	}

	public ProductSearch(Product i) {
		this.id = i.getId();
		this.brand = i.getBrand().getName();
		this.title = i.getName();
		this.secondCategory = i.getSecondCategory().getName();
		this.thirdCategory = i.getThirdCategory().getName();
		this.price = i.getPrice();
		this.imgurl = i.getCoverImg();
		this.serialNo = i.getSerialNO();
		this.url = i.getUrl();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(String secondCategory) {
		this.secondCategory = secondCategory;
	}

	public String getThirdCategory() {
		return thirdCategory;
	}

	public void setThirdCategory(String thirdCategory) {
		this.thirdCategory = thirdCategory;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
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
		ProductSearch other = (ProductSearch) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
