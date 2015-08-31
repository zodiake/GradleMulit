package com.sj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.admin.model.ProductOption;
import com.sj.repository.model.ProductJson;
import com.sj.repository.service.ConsumableService;

@Controller
@RequestMapping("/admin")
public class ConsumableController {
	@Autowired
	private ConsumableService service;

	@RequestMapping(value = "/consumable", method = RequestMethod.GET)
	@ResponseBody
	private Page<ProductJson> list(
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size,
			ProductOption option) {
		return service.findByFirstCategoryAndSecondCategoryAndStatusJson(
				new PageRequest(page, size), option.getFirstCategory(),
				option.getSecondCategory(), option.getState());
	}
}
