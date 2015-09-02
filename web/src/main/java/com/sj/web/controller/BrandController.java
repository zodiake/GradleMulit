package com.sj.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.model.model.Brand;
import com.sj.model.model.Product;
import com.sj.repository.service.ProductService;

@Controller
public class BrandController {
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/brand/{id}", method = RequestMethod.GET)
	public String findByBrand(@PathVariable("id") Long id, Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		Page<Product> products = productService.findByBrand(new PageRequest(
				page - 1, size), new Brand(id));
		uiModel.addAttribute("products", products);
		return null;
	}
	
}
