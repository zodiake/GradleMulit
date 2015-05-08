package com.sj.model.model;

public class UploadResultDetail {
	private String name;
	private long size;
	private String url;
	private String deleteUrl;
	private String deleteType;

	public UploadResultDetail() {
	}

	public UploadResultDetail(String name, long size, String url,
			String deleteUrl, String deleteType) {
		super();
		this.name = name;
		this.size = size;
		this.url = url;
		this.deleteUrl = deleteUrl;
		this.deleteType = deleteType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDeleteUrl() {
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}

	public String getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}
}
