package com.sj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.SubjectCategory;
import com.sj.repository.service.SubjectCategoryService;

@Controller
public class SubjectCategoryController {
	@Autowired
	private SubjectCategoryService service;

	private final String EDIT = "subjectCategory/edit";
	private final String CREATE = "subjectCategory/create";

	@RequestMapping(value = "/admin/subjectCategory/{id}", params = "edit", method = RequestMethod.GET)
	private String edit(Model uiModel, @PathVariable("id") Long id) {
		SubjectCategory sc = service.findOne(id);
		uiModel.addAttribute("sc", sc);
		return EDIT;
	}

	@RequestMapping(value = "/admin/subjectCategory", params = "form", method = RequestMethod.POST)
	private String create(Model uiModel,
			@ModelAttribute("sc") SubjectCategory sc) {
		SubjectCategory result = service.save(sc);
		return "redirect:/admin/subjectCategory/" + result.getId() + "?edit";
	}

	@RequestMapping(value = "/admin/subjectCategory", params = "form", method = RequestMethod.GET)
	private String createForm(Model uiModel) {
		SubjectCategory sc = new SubjectCategory();
		uiModel.addAttribute("sc", sc);
		return CREATE;
	}
}
