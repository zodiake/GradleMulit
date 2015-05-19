package com.sj.repository.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.sj.model.model.Instrument;

@Document(indexName = "sj", type = "instrument")
public class InstrumentSearch {
	@Id
	private Long id;

	// 二级分类
	@Field(type = FieldType.String, store = true, index = FieldIndex.not_analyzed)
	private String secondCategory;

	// 三级分类
	@Field(type = FieldType.String, store = true, index = FieldIndex.not_analyzed)
	private String thirdCategory;

	// 价格
	@Field(type = FieldType.Double, store = true)
	private Float price;

	// 品牌
	@Field(type = FieldType.String, store = true, index = FieldIndex.not_analyzed)
	private String brand;

	// 封面图片
	@Field(type = FieldType.String, store = true, index = FieldIndex.not_analyzed)
	private String imgurl;

	// 标题
	@Field(type = FieldType.String, store = true)
	private String title;

	public InstrumentSearch(Instrument i) {
		this.id = i.getId();
		this.brand = i.getBrand().getName();
		this.title = i.getName();
		this.secondCategory = i.getSecondCategory().getName();
		this.thirdCategory = i.getThirdCategory().getName();
		this.price = i.getPrice();
		this.imgurl = i.getCoverImg();
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
		InstrumentSearch other = (InstrumentSearch) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
