package com.sj.repository.model;

import com.sj.model.model.Solution;

public class SolutionJson {
	private Long id;
	private String name;

	public SolutionJson(Solution s) {
		this.id = s.getId();
		this.name = s.getName();
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
}
