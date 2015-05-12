package com.sj.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.admin.async.AsyncWriteFileService;
import com.sj.model.model.Advertisement;
import com.sj.model.type.AdvertiseCategoryEnum;
import com.sj.repository.service.AdvertisementService;

@Controller
public class AdvertisementController {
	@Autowired
	private AdvertisementService service;
	@Autowired
	private AsyncWriteFileService writeFileService;

	private final String LIST = "advertisement/list";
	private final String EDIT = "advertisement/edit";

	@RequestMapping(value = "/admin/{category}/advertisements", method = RequestMethod.GET)
	public String list(Model uiModel,
			@PathVariable(value = "category") String category) {
		AdvertiseCategoryEnum categoryEnum = AdvertiseCategoryEnum
				.fromString(category);
		List<Advertisement> lists = service.findByCategory(categoryEnum,
				new PageRequest(0, 5, Direction.DESC, "createdTime"));
		uiModel.addAttribute("list", lists);
		return LIST;
	}

	@RequestMapping(value = "/admin/{category}/advertisements/{id}", params = "edit", method = RequestMethod.GET)
	public String edit(Model uiModel,
			@PathVariable(value = "category") AdvertiseCategoryEnum category,
			@PathVariable(value = "id") Long id) {
		Advertisement adv = service.findByIdAndCategory(id, category);
		uiModel.addAttribute("adv", adv);
		return EDIT;
	}

	@RequestMapping(value = "/admin/{category}/advertisements/{id}", method = RequestMethod.PUT)
	public String upload(@ModelAttribute("adv") Advertisement adv,
			@PathVariable("id") Long id,
			@PathVariable("category") AdvertiseCategoryEnum category) {
		adv.setId(id);
		adv.setCategory(category);
		service.update(adv);
		return "redirect:/admin/" + category.toString().toLowerCase()
				+ "/advertisements/" + id + "?edit";
	}
}
