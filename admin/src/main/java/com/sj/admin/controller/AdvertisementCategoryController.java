package com.sj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.admin.annotation.PageRequestAnn;
import com.sj.model.model.AdvertisementCategory;
import com.sj.repository.service.AdvertisementCategoryService;

@Controller
public class AdvertisementCategoryController {
	@Autowired
	private AdvertisementCategoryService service;

	private final String LIST = "advertisement/category/list";

	@RequestMapping(value = "/admin/advertisementCategory", method = RequestMethod.GET)
	public String list(Model uiModel, @PageRequestAnn PageRequest pageRequest) {
		Page<AdvertisementCategory> categories = service.findAll(pageRequest);
		uiModel.addAttribute("lists", categories);
		return LIST;
	}

}
