package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.model.model.Information;
import com.sj.model.model.InformationCategory;
import com.sj.repository.service.InformationCategoryService;
import com.sj.repository.service.InformationService;

@Controller
public class InformationController {

	@Autowired
	private InformationService informationService;
	@Autowired
	private InformationCategoryService informationCategoryService;


	@RequestMapping(value = "/informationCategory/{category}", method = RequestMethod.GET)
	public String findByCategory(
			@PathVariable(value = "category") String category, Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		InformationCategory ic = informationCategoryService.findByUrl(category);
		Page<Information> informations = informationService.findByCategory(
				ic, new PageRequest(page - 1, size));
		uiModel.addAttribute("informations", informations);
		uiModel.addAttribute("category", ic);
		uiModel.addAttribute("pc", new InformationCategory(InformationCategory.ZX));
		return "information/informations";
	}

	@RequestMapping(value = "/informations/{id}", method = RequestMethod.GET)
	public String findOne(@PathVariable(value = "id") Long id, Model uiModel) {
		Information information = informationService.findOne(id);
		uiModel.addAttribute("information", information);
		uiModel.addAttribute("pc", new InformationCategory(InformationCategory.ZX));
		return "information/information";
	}
}