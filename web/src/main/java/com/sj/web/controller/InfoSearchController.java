package com.sj.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.repository.search.model.InfoSearch;
import com.sj.repository.search.model.InfoSearchOption;
import com.sj.repository.search.service.InfoSearchService;

@Controller
public class InfoSearchController extends BaseController<InfoSearch> {
	@Autowired
	private InfoSearchService infoSearchService;

	private final String INFO_SEARCH_LIST = "search/info/info";

	@RequestMapping(value = "/info/_search", method = RequestMethod.GET)
	public String infoSearch(InfoSearchOption option,
			@PageableDefault(page = 0, size = 15) Pageable pageable,
			Model uiModel) {

		Page<InfoSearch> pages = infoSearchService.findByOption(option,
				pageable);
		Map<String, String> map = infoSearchService.buildMap(option);

		ViewPage viewpage = caculatePage(pages);
		viewpage.setOption(map);
		viewpage.setHref("/info/_search");
		viewpage.setCurrent(pageable.getPageNumber());

		uiModel.addAttribute("lists", pages);
		uiModel.addAttribute("option", option);
		uiModel.addAttribute("viewpage", viewpage);
		return INFO_SEARCH_LIST;
	}
}
