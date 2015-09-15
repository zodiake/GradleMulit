package com.sj.web.controller;


import static com.sj.repository.util.RedisConstant.REVIEWCOUNT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.model.model.Subject;
import com.sj.model.model.SubjectCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.service.SubjectCategoryService;
import com.sj.repository.service.SubjectService;
import com.sj.web.exception.SubjectNotFoundException;

@Controller
public class SubjectController extends BaseController<Subject>{
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private SubjectCategoryService subjectCategoryService;
	@Autowired
	private StringRedisTemplate template;

	@RequestMapping(value = "/subjects", method = RequestMethod.GET)
	public String findSubjects(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			Model uiModel) {
		Page<Subject> subjects = subjectService.findByActivated(new PageRequest(page - 1, size),ActivateEnum.ACTIVATE);
		uiModel.addAttribute("subjects", subjects);
		ViewPage viewpage = caculatePage(subjects);
		viewpage.setHref("/subjects");
		uiModel.addAttribute("viewpage", viewpage);
		uiModel.addAttribute("pc", new SubjectCategory(6l));
		return "subject/subjects";
	}

	@RequestMapping(value = "/subjects/{id}", method = RequestMethod.GET)
	public String findOne(@PathVariable("id") Long id, Model uiModel) {
		Subject subject = subjectService.findOne(id);
		if (subject == null)
			throw new SubjectNotFoundException();
		
		Long subjectCount = template.opsForValue().increment(REVIEWCOUNT + id, 1);
		subject.setViewCount(subjectCount);
		uiModel.addAttribute("subject", subject);
		uiModel.addAttribute("pc", new SubjectCategory(6l));
		return "subject/subject";
	}
}
