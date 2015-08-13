package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.model.model.Subject;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.service.SubjectService;
import com.sj.web.exception.SubjectNotFoundException;
import com.sj.web.util.ShowPage;

@Controller
public class SubjectController {
	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = "/subjects", method = RequestMethod.GET)
	public String findSubjects(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size,
			Model uiModel) {
		Page<Subject> subjects = subjectService.findByActivated(
				new PageRequest(page-1, size,Direction.DESC,"createdTime"), ActivateEnum.ACTIVATE);
		ShowPage.showSubject(subjects);
		uiModel.addAttribute("subjects", subjects);
		return "login";
	}
	
//	public String findSubjectBy
	
	@RequestMapping(value = "/subjects/{id}",method = RequestMethod.GET)
	public String findOne(@PathVariable("id")Long id,Model uiModel){
		Subject subject = subjectService.findOne(id);
		if(subject==null)
			throw new SubjectNotFoundException();
		uiModel.addAttribute("subject", subject);
		return null;
	}
}
