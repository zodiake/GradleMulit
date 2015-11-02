package com.sj.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.Brand;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.search.model.ProductSearch;
import com.sj.repository.search.model.ProductSearchOption;
import com.sj.repository.search.model.SortEnum;
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
		return brandService.findByAcitvate(ActivateEnum.ACTIVATE);
	}

	@RequestMapping(value = "/products/_search", method = RequestMethod.GET)
	public String productSearch(@ModelAttribute ProductSearchOption option,
			@PageableDefault(page = 0, size = 12) Pageable pageable,
			Model uiModel, HttpServletRequest request) {

		buildOption(option);
		PageRequest pageRequest = buildPageRequest(option, pageable);

		Page<ProductSearch> results = service.findByOption(option, pageRequest);

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
				option.setFrom(0f);
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
		if(StringUtils.isEmpty(option.getThirdCategory()))
			option.setThirdCategory(null);
		if (StringUtils.isEmpty(option.getTag()))
			option.setTag(null);
		if (StringUtils.isEmpty(option.getTitle()))
			option.setTitle(null);
		if (StringUtils.isEmpty(option.getBrand()))
			option.setBrand(null);
	}

	private PageRequest buildPageRequest(ProductSearchOption option,
			Pageable pageable) {
		if (option.getSort() != null) {
			SortEnum sort = option.getSort();
			Direction direction = null;
			String properties = null;
			switch (sort) {
			case CREATEDTIMEASC:
				direction = Direction.DESC;
				properties = "createdTime";
				break;
			case CREATEDTIMEDESC:
				direction = Direction.ASC;
				properties = "createdTime";
				break;
			case PRICEDESC:
				direction = Direction.DESC;
				properties = "price";
				break;
			case PRICEASC:
				direction = Direction.ASC;
				properties = "price";
				break;
			case REVIEWASC:
				direction = Direction.ASC;
				properties = "review";
				break;
			case REVIEWDESC:
				direction = Direction.DESC;
				properties = "review";
				break;
			}
			return new PageRequest(pageable.getPageNumber(),
					pageable.getPageSize(), direction, properties);
		} else {
			return new PageRequest(pageable.getPageNumber(),
					pageable.getPageSize(), Direction.DESC, "createdTime");
		}

	}
}