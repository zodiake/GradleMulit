package com.sj.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.Brand;
import com.sj.repository.search.model.BrandSearchOption;
import com.sj.repository.service.BrandService;

@Controller
public class BrandController extends BaseController<Brand> {
	@Autowired
	private BrandService brandService;
	
	private final String BRANDLIST = "search/brand/brands";

	@RequestMapping(value = "/brands/_search", method = RequestMethod.GET)
	public String findBrandByName(Model uiModel,
			@ModelAttribute BrandSearchOption option,
			@PageableDefault(page = 0, size = 16) Pageable pageable) {
		Page<Brand> brandPage = brandService.searchBrand(option, pageable);
		
		Map<String, String> map = brandService.buildMap(option);
		
		ViewPage viewPage = caculatePage(brandPage);
		viewPage.setHref("/brands/_search");
		viewPage.setOption(map);
		viewPage.setCurrent(brandPage.getNumber());
		
		uiModel.addAttribute("viewpage", viewPage);
		uiModel.addAttribute("page", brandPage);
		uiModel.addAttribute("option", option);
		uiModel.addAttribute("action", "/brands/_search");
		uiModel.addAttribute("field", "品牌");
		return BRANDLIST;
	}
}
