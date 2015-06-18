package com.sj.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.search.model.ProductSearch;
import com.sj.repository.search.model.ProductSearchOption;
import com.sj.repository.search.service.ProductSearchService;
import com.sj.repository.service.ProductCategoryService;

@Controller
public class SearchController {
	@Autowired
	private ProductSearchService service;
	@Autowired
	private ProductCategoryService categoryService;

	private final String SEARCHLIST = "search/products";

	@ModelAttribute("firstCategory")
	public List<ProductCategory> firstCategory() {
		return categoryService.findAllFirstCategory(ActivateEnum.ACTIVATE);
	}

	@RequestMapping(value = "/products/_search", method = RequestMethod.GET)
	public String productSearch(@ModelAttribute ProductSearchOption option,
			@PageableDefault(page = 0, size = 15) Pageable pageable,
			Model uiModel) {
		Page<ProductSearch> results = service.findByOption(option, pageable);
		if (option.getFirstCategory() != null) {
			List<ProductCategory> categories = categoryService
					.findAllSecondCategory(ActivateEnum.ACTIVATE);
			uiModel.addAttribute("categories", categories);
		}
		uiModel.addAttribute("lists", results);
		uiModel.addAttribute("option", option);
		return SEARCHLIST;
	}
}
