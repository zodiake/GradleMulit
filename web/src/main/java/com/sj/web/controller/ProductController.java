package com.sj.web.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.Product;
import com.sj.repository.search.model.ModelSearchOption;
import com.sj.repository.service.ProductService;

@Controller
public class ProductController extends BaseController<Product>{
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.POST)
	public void addCount(@PathVariable(value = "id") Long id) {
		productService.addViewCount(id);
	}
	
	@RequestMapping(value = "/models/_search", method = RequestMethod.GET)
	public String findBrandByName(Model uiModel,
			@ModelAttribute ModelSearchOption option,
			@PageableDefault(page = 0, size = 12) Pageable pageable) {
		System.out.println(option.getTitle());
		Page<Product> brandPage = productService.findBySearchModel(option, pageable);
		
		Map<String, String> map = productService.buildMap(option);
		
		ViewPage viewPage = caculatePage(brandPage);
		viewPage.setHref("/models/_search");
		viewPage.setOption(map);
		viewPage.setCurrent(pageable.getPageNumber());
		
		uiModel.addAttribute("viewpage", viewPage);
		uiModel.addAttribute("page", brandPage);
		uiModel.addAttribute("option", option);
		uiModel.addAttribute("action", "/models/_search");
		uiModel.addAttribute("field", "型号");
		return "search/model/products";
	}
}