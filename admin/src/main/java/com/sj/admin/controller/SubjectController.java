package com.sj.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.admin.annotation.PageRequestAnn;
import com.sj.admin.exception.SubjectNotFoundException;
import com.sj.admin.util.ActivateState;
import com.sj.model.model.Subject;
import com.sj.model.model.SubjectCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.service.SubjectCategoryService;
import com.sj.repository.service.SubjectService;

@Controller
public class SubjectController {
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private SubjectCategoryService categoryService;

	private final String LIST = "subject/list";
	private final String CREATE = "subject/create";
	private final String EDIT = "subject/edit";

	@RequestMapping(value = "/admin/subjects", method = RequestMethod.GET)
	public String lists(Model uiModel, @PageRequestAnn PageRequest pageRequest,
			@ModelAttribute(value = "state") ActivateState state) {

		Page<Subject> result;
		if (state != null)
			result = subjectService.findByActivated(pageRequest,
					state.getActivateEnum());
		else
			result = subjectService.findAll(pageRequest);
		uiModel.addAttribute("lists", result);
		uiModel.addAttribute("state", state);
		return LIST;
	}

	@RequestMapping(value = "/admin/subjects/{id}", params = "edit", method = RequestMethod.GET)
	public String viewEdit(Model uiModel, @PathVariable("id") Long id) {
		Subject subject = subjectService.findOne(id);
		if (subject == null)
			throw new SubjectNotFoundException();
		List<SubjectCategory> categories = categoryService
				.findByActivate(ActivateEnum.ACTIVATE);
		uiModel.addAttribute("subject", subject);
		uiModel.addAttribute("categories", categories);
		return EDIT;
	}

	@RequestMapping(value = "/admin/subjects", params = "form", method = RequestMethod.GET)
	public String create(Model uiModel) {
		List<SubjectCategory> categories = categoryService
				.findByActivate(ActivateEnum.ACTIVATE);
		uiModel.addAttribute("subject", new Subject());
		uiModel.addAttribute("categories", categories);
		return CREATE;
	}

	@RequestMapping(value = "/admin/subjects", params = "form", method = RequestMethod.POST)
	public String process(@ModelAttribute("subject") Subject subject) {
		Subject result = subjectService.save(subject);
		return "redirect:/admin/subjects/" + result.getId() + "?edit";
	}
}