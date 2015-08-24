package com.sj.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.Brand;
import com.sj.repository.service.BrandService;

@Controller
public class BrandController {
	@Autowired
	private BrandService brandService;

	@RequestMapping(value = "/admin/brands", method = RequestMethod.GET)
	public String findAll(Model uiModel,
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size) {
		brandService.findAll(new PageRequest(page - 1, size));
		return null;
	}

	@RequestMapping(value = "/admin/brands/{id}", method = RequestMethod.DELETE)
	public String deleteOne(@PathVariable("id") Long id) {
		brandService.deleteOne(id);
		return null;
	}

	@RequestMapping(value = "/admin/brands", method = RequestMethod.GET, params = "form")
	@ResponseBody
	public String create(Model uiModel) {
		uiModel.addAttribute("brand", new Brand());
		return null;
	}

	@RequestMapping(value = "/admin/brands", method = RequestMethod.POST, params = "form")
	public String createProcess(@Valid @ModelAttribute("brand") Brand brand,
			BindingResult result, Model uiModel) {
		if (result.hasErrors()) {
			uiModel.addAttribute("brand", brand);
		}
		brandService.save(brand);
		return null;
	}

}
