package com.sj.repository.model;

import com.sj.model.model.Solution;
import com.sj.model.type.ActivateEnum;

public class SolutionDetailJson {
	private Long id;
	private String name;
	private ActivateEnum active;

	public SolutionDetailJson(Solution s) {
		this.id = s.getId();
		this.name = s.getName();
		this.active = s.getActive();
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

	public ActivateEnum getActive() {
		return active;
	}

	public void setActive(ActivateEnum active) {
		this.active = active;
	}
}
