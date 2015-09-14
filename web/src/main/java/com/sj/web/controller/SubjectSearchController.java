package com.sj.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.SubjectCategory;
import com.sj.repository.search.model.SubjectSearch;
import com.sj.repository.search.model.SubjectSearchOption;
import com.sj.repository.search.service.SubjectSearchService;
import com.sj.repository.service.SubjectCategoryService;

@Controller
public class SubjectSearchController extends BaseController<SubjectSearch> {
	@Autowired
	private SubjectSearchService service;
	@Autowired
	private SubjectCategoryService categoryService;

	private final String SEARCH_LIST = "search/subject/subjects";

	@RequestMapping(value = "/subject/_search", method = RequestMethod.GET)
	public String infoSearch(SubjectSearchOption option,
			@PageableDefault(page = 0, size = 15) Pageable pageable,
			Model uiModel) {

		Page<SubjectSearch> pages = service.findByOption(option, pageable);
		Map<String, String> map = service.buildMap(option);
		List<SubjectCategory> categories = categoryService.findAll();

		ViewPage viewpage = caculatePage(pages);
		viewpage.setOption(map);
		viewpage.setHref("/info/_search");
		viewpage.setCurrent(pageable.getPageNumber());

		uiModel.addAttribute("subjects", pages);
		uiModel.addAttribute("option", option);
		uiModel.addAttribute("viewpage", viewpage);
		uiModel.addAttribute("categories", categories);
		return SEARCH_LIST;
	}

}
