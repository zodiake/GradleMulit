package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.Product;
import com.sj.repository.service.ProductService;
import com.sj.web.exception.ProductNotFoundException;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	private final String DETAIL = "product/detail";

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public String view(Model uiModel, @PathVariable(value = "id") Long id) {
		Product product = productService.findOne(id);
		productService.addViewCount(id);
		if (product == null)
			throw new ProductNotFoundException();
		uiModel.addAttribute("product", product);
		return DETAIL;
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.POST)
	public void addCount(@PathVariable(value = "id") Long id) {
		productService.addViewCount(id);
	}
}