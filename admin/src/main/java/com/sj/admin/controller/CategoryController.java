package com.sj.admin.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sj.admin.exception.CategoryNotFoundException;
import com.sj.admin.security.UserContext;
import com.sj.model.model.Category;
import com.sj.repository.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserContext userContext;

	private final String LIST = "category/list";
	private final String EDIT = "category/edit";
	private final String FORM = "category/form";

	/*-----------------first category------------------------*/
	// all first category list
	@RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
	public String findAllFirstCategory(Model uiModel) {
		Page<Category> lists = categoryService.findByParent(null, null);
		uiModel.addAttribute("results", lists);
		return LIST;
	}

	// get form to add a category
	@RequestMapping(value = "/admin/categories", params = "form", method = RequestMethod.GET)
	public String newFirstCategory(Model uiModel) {
		uiModel.addAttribute("category", new Category());
		return FORM;
	}

	// new first category
	@RequestMapping(value = "/admin/categories", params = "form", method = RequestMethod.POST)
	public String processFirstCategory(
			@Valid @ModelAttribute("category") Category category,
			BindingResult bindingResult, Model uiModel,
			RedirectAttributes redirectAttr) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("category", category);
			return FORM;
		}
		category.setCreatedBy(userContext.getCurrnetUser().getName());
		Category temp = categoryService.save(category);
		redirectAttr.addFlashAttribute("message", "success");
		return "redirect:/admin/categories/" + temp.getId() + "?edit";
	}

	/*---------------------end first category----------------------------------*/

	// child category list
	@RequestMapping(value = "/admin/{parent}/categories", method = RequestMethod.GET)
	public String findByParent(@PathVariable(value = "parent") Long parent,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size,
			Model uiModel) {
		Page<Category> results = categoryService.findByParent(new PageRequest(
				page - 1, size, Direction.DESC, "createdTime"), new Category(
				parent));
		uiModel.addAttribute("results", results);
		return LIST;
	}

	// get form to add a category
	@RequestMapping(value = "/admin/{parent}/categories", params = "form", method = RequestMethod.GET)
	public String formChildCategory(Model uiModel) {
		uiModel.addAttribute("category", new Category());
		return FORM;
	}

	// add category
	@RequestMapping(value = "/admin/{parent}/categories", params = "form", method = RequestMethod.POST)
	public String addChildCategory(@PathVariable(value = "parent") Long parent,
			@Valid @ModelAttribute(value = "category") Category category,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		Category parentCategory = categoryService.findOne(parent);
		if (parentCategory == null)
			throw new CategoryNotFoundException();
		category.setParent(parentCategory);
		Category result = categoryService.save(category);
		redirectAttributes.addFlashAttribute("message", "success");
		return "redirect:/admin/categories/" + result.getId() + "?edit";
	}

	// get category to edit
	@RequestMapping(value = "/admin/categories/{id}", params = "edit", method = RequestMethod.GET)
	public String view(@PathVariable(value = "id") Long id, Model uiModel) {
		Category category = categoryService.findOne(id);
		if (category == null)
			throw new CategoryNotFoundException();
		uiModel.addAttribute("category", category);
		return EDIT;
	}

	// update category info
	@RequestMapping(value = "/admin/categories/{id}", params = "edit", method = RequestMethod.PUT)
	public String view(@PathVariable(value = "id") Long id, Model uiModel,
			@Valid @ModelAttribute("category") Category category,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("category", category);
			return EDIT;
		}
		category.setId(id);
		category.setUpdatedBy(userContext.getCurrnetUser().getName());
		Category result = categoryService.save(category);
		uiModel.addAttribute("category", result);
		return "redirect:/admin/categories/" + id + "?edit";
	}
}
