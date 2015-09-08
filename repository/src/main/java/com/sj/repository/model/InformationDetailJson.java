package com.sj.repository.model;

import com.sj.model.model.Information;

public class InformationDetailJson extends InformationJson {
	private String content;

	public InformationDetailJson(Information info) {
		super(info);
		this.content = info.getContent().getContent();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
