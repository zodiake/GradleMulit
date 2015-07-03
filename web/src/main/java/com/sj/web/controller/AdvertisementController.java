package com.sj.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.model.model.Advertisement;
import com.sj.model.type.AdvertiseCategoryEnum;
import com.sj.repository.service.AdvertisementService;

@Controller
public class AdvertisementController {
	@Autowired
	private AdvertisementService advertisementService;

	@RequestMapping(value = "/{category}/advertisements", method = RequestMethod.GET)
	public String findAdvertisementByCategory(
			@PathVariable("category") String category,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size,
			Model uiModel) {
		List<Advertisement> advertisements = advertisementService
				.findByCategory(AdvertiseCategoryEnum.fromString(category),
						new PageRequest(page, size));
		uiModel.addAttribute("", advertisements);
		return null;
	}

	@RequestMapping(value = "/{category}/advertisements/{id}", method = RequestMethod.GET)
	public String findAdvertisement(@PathVariable("category") String category,
			@PathVariable("id") Long id, Model uiModel) {
		Advertisement advertisement = advertisementService.findByIdAndCategory(
				id, AdvertiseCategoryEnum.fromString(category));
		uiModel.addAttribute("advertisment", advertisement);
		return null;
	}
}
