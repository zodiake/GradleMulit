package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.repository.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService service;

	@ResponseBody
	@RequestMapping(value = "/category")
	public String find() {
		service.findById(1);
		return "hello";
	}
}
