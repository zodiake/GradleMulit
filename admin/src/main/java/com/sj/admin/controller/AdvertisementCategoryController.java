package com.sj.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.admin.annotation.PageRequestAnn;
import com.sj.admin.exception.CategoryNotFoundException;
import com.sj.model.model.AdvertisementCategory;
import com.sj.repository.service.AdvertisementCategoryService;

@Controller
public class AdvertisementCategoryController {
	@Autowired
	private AdvertisementCategoryService service;

	private final String LIST = "advertisement/category/list";
	private final String CREATE = "advertisement/category/create";
	private final String CREATEOK = "";
	private final String EDIT = "advertisement/category/edit";
	private final String EDITOK = "";

	@RequestMapping(value = "/admin/advertisementCategory", method = RequestMethod.GET)
	public String list(Model uiModel, @PageRequestAnn PageRequest pageRequest) {
		Page<AdvertisementCategory> categories = service.findAll(pageRequest);
		uiModel.addAttribute("lists", categories);
		return LIST;
	}

	@RequestMapping(value = "/admin/advertisementCategory", params = "form", method = RequestMethod.GET)
	public String createAdvertisementCategory(Model uiModel) {
		uiModel.addAttribute("advertisementCategory",
				new AdvertisementCategory());
		return CREATE;
	}

	@RequestMapping(value = "/admin/advertisementCategory", params = "form", method = RequestMethod.POST)
	public String createAdvertisementCategoryProcess(
			Model uiModel,
			@Valid @ModelAttribute("advertisementCategory") AdvertisementCategory advertisementCategory,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("advertisementCategory", advertisementCategory);
			return CREATE;
		}
		advertisementCategory = service.save(advertisementCategory);
		uiModel.addAttribute("advertisementCategory", advertisementCategory);
		return CREATEOK;
	}

	@RequestMapping(value = "/admin/advertisementCategory/{id}", params = "edit", method = RequestMethod.GET)
	public String editAdvertisementCategory(Model uiModel,
			@PathVariable("id") Long id) {
		AdvertisementCategory advertisementCategory = service.findOne(id);
		if (advertisementCategory == null)
			throw new CategoryNotFoundException();
		uiModel.addAttribute("advertisementCategory", advertisementCategory);
		return EDIT;
	}

	@RequestMapping(value = "/admin/advertisementCategory/{id}", params = "edit", method = RequestMethod.PUT)
	public String editAdvertisementCategoryProcess(
			Model uiModel,
			@PathVariable("id") Long id,
			@Valid @ModelAttribute("advertisementCategory") AdvertisementCategory advertisementCategory,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("advertisementCategory", advertisementCategory);
			return EDIT;
		}
		advertisementCategory.setId(id);
		service.update(advertisementCategory);
		return EDITOK;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/advertisementCategory/{id}", method = RequestMethod.DELETE)
	public String deleteAdvertisementCategory(@PathVariable("id") Long id) {
		service.delete(id);
		return "success";
	}

}
