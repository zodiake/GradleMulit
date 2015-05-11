package com.sj.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.ScrollImage;
import com.sj.repository.service.ScrollImageService;

@Controller
public class ScrollImageController {
	@Autowired
	private ScrollImageService service;

	private final String LISTS = "scroll/list";

	@RequestMapping(value = "/admin/scrollImages", method = RequestMethod.GET)
	public String lists(Model uiModel) {
		List<ScrollImage> result = service.findAll();
		uiModel.addAttribute("lists", result);
		return LISTS;
	}

}
