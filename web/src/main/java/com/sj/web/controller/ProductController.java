package com.sj.web.controller;


import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.Product;
import com.sj.model.model.Review;
import com.sj.repository.service.BrandService;
import com.sj.repository.service.PreferProductService;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.ProductService;
import com.sj.repository.service.ReviewService;
import com.sj.web.exception.ProductNotFoundException;
import com.sj.web.security.SiteUserContext;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ProductCategoryService pcService;
	@Autowired
	private PreferProductService preferProductService;
	@Autowired
	private SiteUserContext userContext;
	@Autowired
	private ReviewService reviewService;

	private final String DETAIL = "product/product";

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public String view(Model uiModel, @PathVariable(value = "id") Long id) {
		Product product = productService.findOne(id);
		if (product == null)
			throw new ProductNotFoundException();
		productService.addViewCount(id);
		uiModel.addAttribute("product", product);
		Page<Review> reviewPage = reviewService.findByProduct(product, new PageRequest(0, 10,Direction.DESC,"createdTime"));
		uiModel.addAttribute("reviewPage", reviewPage);
		uiModel.addAttribute("nowTime", Calendar.getInstance().getTime().getTime());
		uiModel.addAttribute("pc", product.getFirstCategory());
		return DETAIL;
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.POST)
	public void addCount(@PathVariable(value = "id") Long id) {
		productService.addViewCount(id);
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET, params = "form")
	public String create(Model uiModel) {
		uiModel.addAttribute("product", new Product());
		return null;
	}

}