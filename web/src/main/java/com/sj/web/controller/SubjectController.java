package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	public SubjectService subjectService;

	@RequestMapping(value="/subjects",method=RequestMethod.GET)
	// delete
	public String findSubjectShowOnIndex(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size,
			Model uiModel) {
		Page<Subject> subjects = subjectService.findByActivated(
				new PageRequest(page, size), ActivateEnum.valueOf("1"));
		uiModel.addAttribute("subjects", subjects);
		return null;
	}
	
//	public String 
}
