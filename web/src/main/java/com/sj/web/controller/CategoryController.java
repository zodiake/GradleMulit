package com.sj.web.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService service;

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String findAll() {
		Set<ProductCategory> categories = service.findAll();
		return "home";
	}

	// child category list
	@RequestMapping(value = "/category/{parent}/categories", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> findByParent(
			@PathVariable(value = "parent") Long parent,
			@PageableDefault(size = 15) Pageable pageable, Model uiModel) {
		List<ProductCategory> results = service.findByParentAndActivate(
				new ProductCategory(parent), ActivateEnum.ACTIVATE);
		List<Category> categories = results.stream()
				.map((r) -> new Category(r.getId(), r.getName()))
				.collect(Collectors.toList());
		return categories;
	}

	private class Category {
		private Long id;
		private String name;

		public Category(Long id, String name) {
			this.id = id;
			this.name = name;
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
}
