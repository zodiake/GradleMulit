package com.sj.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.sj.repository.service.ProductCategoryService;

@Controller
public class CategoryController {
	@Autowired
	private ProductCategoryService service;


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
