package com.sj.web.controller;

import static com.sj.repository.util.RedisConstant.INFORMATIONCOUNT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
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
import com.sj.web.exception.CategoryNotFoundException;
import com.sj.web.exception.InformationNotFoundException;

@Controller
public class InformationController {

	@Autowired
	private InformationService informationService;
	@Autowired
	private InformationCategoryService informationCategoryService;
	@Autowired
	private StringRedisTemplate template;


	@RequestMapping(value = "/informationCategorys/{category}", method = RequestMethod.GET)
	public String findByCategory(
			@PathVariable(value = "category") String category, Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		InformationCategory ic = informationCategoryService.findByName(category.trim());
		if(ic == null)
			throw new CategoryNotFoundException();
		Page<Information> informations = informationService.findByCategory(
				ic, new PageRequest(page - 1, size));
		uiModel.addAttribute("informations", informations);
		uiModel.addAttribute("category", ic);
		uiModel.addAttribute("pc", informationCategoryService.findOne(5l));
		return "information/informations";
	}

	@RequestMapping(value = "/informations/{id}", method = RequestMethod.GET)
	public String findOne(@PathVariable(value = "id") Long id, Model uiModel) {
		Information information = informationService.findOne(id);
		if(information==null)
			throw new InformationNotFoundException();
		Long inforCount = template.opsForValue().increment(INFORMATIONCOUNT + id, 1);
		information.setViewCount(inforCount);
		uiModel.addAttribute("information", information);
		uiModel.addAttribute("pc", informationCategoryService.findOne(5l));
		return "information/information";
	}
}
