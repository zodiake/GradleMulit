package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sj.model.model.Product;
import com.sj.repository.publisher.ProductSavedEventPublisher;

@Controller
public class IndexController {
	@Autowired
	private ProductSavedEventPublisher publisher;

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		publisher.publish(new Product());
		return "index";
	}
}
