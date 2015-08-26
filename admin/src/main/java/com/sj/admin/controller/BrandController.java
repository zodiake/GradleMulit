package com.sj.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.BrandJson;
import com.sj.repository.service.BrandService;

@Controller
public class BrandController {
	@Autowired
	private BrandService brandService;

	@RequestMapping(value = "/admin/brands", method = RequestMethod.GET)
	@ResponseBody
	public Page<BrandJson> findAll(Model uiModel, HttpServletRequest request,
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size) {
		String state = request.getParameter("state");
		ActivateEnum activate = ActivateEnum.fromString(state);
		return brandService.findByActivateToJson(activate, new PageRequest(
				page - 1, size, Direction.DESC, "createdTime"));
	}

	@RequestMapping(value = "/admin/brands/{id}/activate", method = RequestMethod.POST)
	@ResponseBody
	public String updateActivate(@PathVariable("id") Long id,
			HttpServletRequest request) {
		String state = request.getParameter("state");
		ActivateEnum activate = ActivateEnum.fromString(state);
		brandService.activate(id, activate);
		return "{\"data\":\"success\"}";
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
