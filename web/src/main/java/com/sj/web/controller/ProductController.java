package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.repository.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public String view() {
		return "";
	}

}
