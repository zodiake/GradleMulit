package com.sj.admin.controller;


import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.admin.exception.CategoryNotFoundException;
import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.service.ProductCategoryService;

@Controller
public class CategroyController {
	private final String LIST = "category/list";
	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/admins/categories", method = RequestMethod.GET)
	public String findFirstCategroy(Model uiModel) {
		List<ProductCategory> categories = productCategoryService
				.findAllFirstCategory(ActivateEnum.ACTIVATE);
		uiModel.addAttribute("results", categories);
		return LIST;
	}

	@RequestMapping(value = "/admin/categories/{parent}/categories", method = RequestMethod.GET)
	public String findCategoryByParent(@PathVariable("id") Long id,
			Model uiModel,
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size) {
		Page<ProductCategory> categories = productCategoryService.findByParent(
				new PageRequest(page, size), new ProductCategory(id));
		uiModel.addAttribute("categories", categories);
		return LIST;
	}

	@RequestMapping(value = "/admin/categories/{id}", method = RequestMethod.GET)
	public String findCategory(@PathVariable("id") Long id, Model uiModel) {
		ProductCategory productCategory = productCategoryService.findOne(id);
		uiModel.addAttribute("categroy", productCategory);
		return null;
	}

	@RequestMapping(value = "/admin/categories", params = "form", method = RequestMethod.POST)
	// need
	public String addCategoryProcess(
			@Valid @ModelAttribute("category") ProductCategory productCategory,
			BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("category", productCategory);
			return null;
		}
		productCategory = productCategoryService.save(productCategory);
		uiModel.addAttribute("category", productCategory);
		return null;
	}

	@RequestMapping(value = "/admin/categories", params = "form", method = RequestMethod.GET)
	public String addCategory(Model uiModel) {
		uiModel.addAttribute("category", new ProductCategory());
		return null;
	}

	@RequestMapping(value = "/admin/categories/{id}", params = "edit", method = RequestMethod.GET)
	public String editCategroy(Model uiModel, @PathVariable("id") Long id) {
		ProductCategory productCategory = productCategoryService.findOne(id);
		if(productCategory==null)
			throw new CategoryNotFoundException();
		uiModel.addAttribute("category", productCategory);
		return null;
	}

	@RequestMapping(value = "/admin/categories/{id}", params = "edit", method = RequestMethod.PUT)
	public String editCategoryProcess(
			@Valid @ModelAttribute("category") ProductCategory productCategory,
			BindingResult bindingResult, @PathVariable("id") Long id,
			Model uiModel) {
		if(bindingResult.hasErrors()){
			uiModel.addAttribute("category", productCategory);
			return null;
		}
		productCategory.setId(id);
		productCategory = productCategoryService.update(productCategory);
		uiModel.addAttribute("category", productCategory);
		return null;
	}
}
