package com.sj.model.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.sj.model.type.ActivateEnum;

@Entity
@Table(name = "advertisement")
public class Advertisement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private ActivateEnum activate;
	@Transient
	private MultipartFile mf;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private AdvertismentCategory category;

	@Column(name = "cover_img")
	private String coverImg;

	@Column(name = "url")
	private String url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}

	public ActivateEnum getActivate() {
		return activate;
	}

	public void setActivate(ActivateEnum activate) {
		this.activate = activate;
	}

	public MultipartFile getMf() {
		return mf;
	}

	public void setMf(MultipartFile mf) {
		this.mf = mf;
	}

	public AdvertismentCategory getCategory() {
		return category;
	}

	public void setCategory(AdvertismentCategory category) {
		this.category = category;
	}
}
