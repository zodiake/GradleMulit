package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.model.model.Subject;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.service.SubjectService;

@Controller
public class SubjectController {
	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = "/admin/subjects", method = RequestMethod.GET)
	public String lists(Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size,
			@RequestParam(value = "state", defaultValue = "-1") Integer state) {

		Page<Subject> result;
		if (state > 2 || state <= -1)
			result = subjectService.findAll(new PageRequest(page - 1, size,
					Direction.DESC, "createdTime"));
		else
			result = subjectService.findByActivated(new PageRequest(page - 1,
					size, Direction.DESC), ActivateEnum.values()[state]);
		uiModel.addAttribute("lists", result);
		uiModel.addAttribute("state", state);
		return null;
	}
}
