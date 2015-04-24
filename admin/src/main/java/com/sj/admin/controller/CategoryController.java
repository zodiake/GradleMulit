package com.sj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sj.repository.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	private final String LIST = "category/list";

	@RequestMapping(value = "/admin/categories")
	public String findAllFirstCategory(Model uiModel) {
		categoryService.findByParent(null,null);
		return LIST;
	}

	@RequestMapping(value = "/admin/{parent}/categories")
	public String findByParent(@PathVariable(value="parent")Long parent){
		return LIST;
	}
}
