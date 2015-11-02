package com.sj.web.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
			@PageableDefault(page = 0, size = 16) Pageable pageable,
			Model uiModel) {
		buildOption(option);

		Page<SubjectSearch> pages = service.findByOption(option, pageable);
		Map<String, String> map = service.buildMap(option);
		List<SubjectCategory> categories = categoryService.findByParent();

		ViewPage viewpage = caculatePage(pages);
		viewpage.setOption(map);
		viewpage.setHref("/subject/_search");
		viewpage.setCurrent(pageable.getPageNumber());

		uiModel.addAttribute("subjects", pages);
		uiModel.addAttribute("option", option);
		uiModel.addAttribute("viewpage", viewpage);
		uiModel.addAttribute("categories", categories);
		uiModel.addAttribute("action", "/subject/_search");
		uiModel.addAttribute("field", "方案");
		return SEARCH_LIST;
	}

	private void buildOption(SubjectSearchOption option) {
		if (StringUtils.isEmpty(option.getTitle()))
			option.setTitle(null);
	}

}
