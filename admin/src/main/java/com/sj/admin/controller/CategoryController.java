package com.sj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sj.repository.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	private final String FIRSTCATEGORY = "category/firstCategoryList";

	@RequestMapping(value = "/admin/first/categories")
	public String findAllFirstCategory(Model uiModel) {
		categoryService.findByParent(null);
		return FIRSTCATEGORY;
	}
}
