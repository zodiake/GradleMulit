package com.sj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.admin.exception.SubjectNotFoundException;
import com.sj.model.model.Subject;
import com.sj.repository.model.SubjectDetailJson;
import com.sj.repository.model.SubjectJson;
import com.sj.repository.service.SubjectService;

@Controller
public class SubjectController {
	@Autowired
	private SubjectService subjectService;

	private final String LIST = "subject/list";

	@RequestMapping(value = "/admin/subjects", method = RequestMethod.GET)
	@ResponseBody
	public Page<SubjectJson> lists(Model uiModel,
			@PageableDefault(page = 0, size = 15) Pageable pageable) {

		return subjectService.findAllJson(new PageRequest(pageable
				.getPageNumber() - 1, pageable.getPageSize()));
	}

	@RequestMapping(value = "/admin/subjects", method = RequestMethod.POST)
	@ResponseBody
	public String createProcess(@ModelAttribute("subject") Subject subject) {
		subjectService.save(subject);
		return "\"success\"";
	}

	@RequestMapping(value = "/admin/subjects/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SubjectDetailJson edit(@PathVariable("id") Long id, Model uiModel) {
		Subject subject = subjectService.findOne(id);
		return new SubjectDetailJson(subject);
	}

	@RequestMapping(value = "/admin/subjects/{id}", method = RequestMethod.PUT)
	public String editProcess(@PathVariable("id") Long id, Model uiModel,
			@ModelAttribute("subject") Subject subject,
			BindingResult bindingResult) {
		Subject oldSubject = subjectService.findOne(id);
		if (oldSubject == null)
			throw new SubjectNotFoundException();
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("subject", subject);
			return null;
		}
		return null;
	}
}