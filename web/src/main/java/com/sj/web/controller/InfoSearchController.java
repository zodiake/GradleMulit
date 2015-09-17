package com.sj.web.controller;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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
		buildOption(option);
		PageRequest pageRequest = new PageRequest(pageable.getPageNumber(),
				pageable.getPageSize(), Direction.DESC, "createdTime");

		Page<InfoSearch> pages = infoSearchService.findByOption(option,
				pageRequest);
		Map<String, String> map = infoSearchService.buildMap(option);

		ViewPage viewpage = caculatePage(pages);
		viewpage.setOption(map);
		viewpage.setHref("/info/_search");
		viewpage.setCurrent(pageable.getPageNumber());

		uiModel.addAttribute("informations", pages);
		uiModel.addAttribute("option", option);
		uiModel.addAttribute("viewpage", viewpage);
		uiModel.addAttribute("action", "/info/_search");
		uiModel.addAttribute("field", "咨询");
		return INFO_SEARCH_LIST;
	}

	private void buildOption(InfoSearchOption option) {
		if (StringUtils.isEmpty(option.getTitle()))
			option.setTitle(null);
	}
}
