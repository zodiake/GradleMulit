package com.sj.repository.model;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.sj.model.model.Subject;

public class SubjectDetailJson {
	private Long id;
	private String name;
	private String img;
	private Calendar createdTime;
	private List<SolutionDetailJson> solutions;

	public SubjectDetailJson(Subject s) {
		this.id = s.getId();
		this.name = s.getName();
		this.img = s.getImage();
		this.createdTime = s.getCreatedTime();
		this.solutions = s.getSolutions().stream()
				.map(c -> new SolutionDetailJson(c))
				.collect(Collectors.toList());
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	public List<SolutionDetailJson> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<SolutionDetailJson> solutions) {
		this.solutions = solutions;
	}

}