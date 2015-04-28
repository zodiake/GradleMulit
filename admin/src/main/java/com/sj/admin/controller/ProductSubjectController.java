package com.sj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.repository.service.ProductSubjectService;

@Controller
public class ProductSubjectController {
	@Autowired
	private ProductSubjectService service;
	
	private final String LIST = "subject/list";

	@RequestMapping(value = "/admin/subjectProducts", method = RequestMethod.GET)
	public String list() {
		return "";
	}

	@RequestMapping(value = "/admin/subjects/{subjectId}/products", method = RequestMethod.GET)
	public String view(@PathVariable("subjectId") Long subjectId,
			@PathVariable("productId") Long productId, Model uiModel) {
		return "";
	}

}