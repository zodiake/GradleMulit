package com.sj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.admin.exception.SubjectNotFoundException;
import com.sj.admin.util.ActivateState;
import com.sj.model.model.Subject;
import com.sj.repository.service.SubjectService;

@Controller
public class SubjectController {
	@Autowired
	private SubjectService subjectService;

	private final String LIST = "subject/list";

	@RequestMapping(value = "/admin/subjects", method = RequestMethod.GET)
	public String lists(Model uiModel,
			@PageableDefault(page = 0, size = 15) Pageable pageable,
			@ModelAttribute(value = "state") ActivateState state) {

		Page<Subject> result;
		if (state != null)
			result = subjectService.findByActivated(pageable,
					state.getActivateEnum());
		else
			result = subjectService.findAll(pageable);
		uiModel.addAttribute("lists", result);
		uiModel.addAttribute("state", state);
		return LIST;
	}

	@RequestMapping(value = "/admin/subjects", params = "form", method = RequestMethod.GET)
	public String create(Model uiModel) {
		uiModel.addAttribute("subject", new Subject());
		return null;
	}

	@RequestMapping(value = "/admin/subjects", method = RequestMethod.POST)
	public String createProcess(@ModelAttribute("subject") Subject subject) {
		Subject result = subjectService.save(subject);
		return "redirect:/admin/subjects/" + result.getId() + "?edit";
	}

	@RequestMapping(value = "/admin/subjects/{id}", params = "edit", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model uiModel) {
		Subject subject = subjectService.findOne(id);
		if (subject == null)
			throw new SubjectNotFoundException();
		uiModel.addAttribute("subject", subject);
		return null;
	}

	@RequestMapping(value = "/admin/subjects/{id}", method = RequestMethod.PUT)
	public String editProcess(@PathVariable("id") Long id, Model uiModel,
			@ModelAttribute("subject") Subject subject,
			BindingResult bindingResult) {
		Subject oldSubject = subjectService.findOne(id);
		if(oldSubject==null)
			throw new SubjectNotFoundException();
		if(bindingResult.hasErrors()){
			uiModel.addAttribute("subject", subject);
			return null;
		}
		return null;
	}
}