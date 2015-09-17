package com.sj.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.Brand;
import com.sj.repository.search.model.ProductSearch;
import com.sj.repository.search.model.ProductSearchOption;
import com.sj.repository.search.service.ProductSearchService;
import com.sj.repository.search.service.SubjectSearchService;
import com.sj.repository.service.BrandService;
import com.sj.repository.service.ProductCategoryService;

@Controller
public class SearchController extends BaseController<ProductSearch> {
	@Autowired
	private ProductSearchService service;
	@Autowired
	private ProductCategoryService categoryService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private SubjectSearchService subjectSearchService;

	private final String SEARCHLIST = "search/products";

	@ModelAttribute("brands")
	public List<Brand> brands() {
		return brandService.findAll();
	}

	@RequestMapping(value = "/products/_search", method = RequestMethod.GET)
	public String productSearch(@ModelAttribute ProductSearchOption option,
			@PageableDefault(page = 0, size = 16) Pageable pageable,
			Model uiModel) {
		buildOption(option);

		Page<ProductSearch> results;
		if (option.getModel() != null) {
			results = service.findByModel(option.getModel(), pageable);
		} else {
			results = service.findByOption(option, pageable);
		}

		Map<String, String> map = service.buildMap(option);

		ViewPage viewpage = caculatePage(results);
		viewpage.setOption(map);
		viewpage.setHref("/products/_search");
		viewpage.setCurrent(pageable.getPageNumber());

		uiModel.addAttribute("products", results);
		uiModel.addAttribute("option", option);
		uiModel.addAttribute("viewpage", viewpage);
		uiModel.addAttribute("brand", option.getBrand());
		return SEARCHLIST;
	}

	private void buildOption(ProductSearchOption option) {
		if (option.getPriceRange() != null) {
			Integer i = Integer.parseInt(option.getPriceRange());
			switch (i) {
			case 1:
				option.setFrom(10000f);
				option.setTo(30000f);
				break;
			case 2:
				option.setFrom(30000f);
				option.setTo(50000f);
				break;
			case 3:
				option.setFrom(50000f);
				option.setTo(70000f);
				break;
			}
		}
		if (StringUtils.isEmpty(option.getSecondCategory()))
			option.setSecondCategory(null);
		if (StringUtils.isEmpty(option.getTag()))
			option.setTag(null);
		if (StringUtils.isEmpty(option.getTitle()))
			option.setTitle(null);
		if (StringUtils.isEmpty(option.getBrand()))
			option.setBrand(null);
	}
}
