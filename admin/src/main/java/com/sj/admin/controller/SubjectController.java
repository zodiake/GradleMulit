package com.sj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.admin.util.ActivateState;
import com.sj.model.model.Subject;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.service.SubjectService;

@Controller
public class SubjectController {
	@Autowired
	private SubjectService subjectService;

	private final String LIST = "subject/list";

	@RequestMapping(value = "/admin/subjects", method = RequestMethod.GET)
	public String lists(Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size,
			@ModelAttribute(value = "state") ActivateState state) {

		Page<Subject> result;
		if (state != null)
			result = subjectService.findByActivated(new PageRequest(page - 1,
					size, Direction.DESC, "createdTime"), state
					.getActivateEnum());
		else
			result = subjectService.findAll(new PageRequest(page - 1, size,
					Direction.DESC, "createdTime"));
		uiModel.addAttribute("lists", result);
		uiModel.addAttribute("state", state);
		return LIST;
	}
}
