package com.sj.web.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.Category;
import com.sj.repository.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService service;

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String findAll() {
		Set<Category> categories = service.findAll();
		return "home";
	}
}
