package com.sj.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.Solution;
import com.sj.model.model.Subject;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.SolutionJson;
import com.sj.repository.model.SubjectDetailJson;
import com.sj.repository.model.SubjectJson;
import com.sj.repository.service.SubjectService;

@Controller
public class SubjectController {
	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = "/admin/subjects", method = RequestMethod.GET)
	@ResponseBody
	public Page<SubjectJson> lists(Model uiModel,
			@PageableDefault(page = 0, size = 15) Pageable pageable) {
		return subjectService.findAllJson(new PageRequest(pageable
				.getPageNumber() - 1, pageable.getPageSize()));
	}

	@RequestMapping(value = "/admin/subjects", method = RequestMethod.POST)
	@ResponseBody
	public String createProcess(@ModelAttribute("subject") Subject subject,
			HttpServletRequest request) {
		String solution = request.getParameter("solution");
		List<Solution> solutions = convertStringToSolution(solution);
		if (solutions != null) {
			solutions.forEach(c -> c.setSubject(subject));
			subject.setSolutions(solutions);
		}
		subject.setActivate(ActivateEnum.ACTIVATE);
		Subject s = subjectService.save(subject);
		return "{\"id\":\"" + s.getId() + "\"}";
	}

	private List<Solution> convertStringToSolution(String solution) {
		if (StringUtils.isNotEmpty(solution)) {
			String[] strings = solution.split(",");
			List<Solution> solutions = new ArrayList<>();
			for (int i = 0; i < strings.length; i++) {
				solutions.add(new Solution(solution));
			}
			return solutions;
		}
		return null;
	}

	@RequestMapping(value = "/admin/subjects/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SubjectDetailJson edit(@PathVariable("id") Long id, Model uiModel) {
		Subject subject = subjectService.findOne(id);
		return new SubjectDetailJson(subject);
	}

	@RequestMapping(value = "/admin/subjects/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String update(@PathVariable("id") Long id, Subject subject,
			HttpServletRequest request) {
		subject.setId(id);
		Subject s = subjectService.update(subject);
		return "{\"id\":\"" + s.getId() + "\"}";
	}

	@RequestMapping(value = "/admin/subject/{id}/state", method = RequestMethod.POST)
	@ResponseBody
	public String update(@PathVariable("id") Long id, HttpServletRequest request) {
		String active = request.getParameter("active");
		ActivateEnum activeEnum = ActivateEnum.fromString(active);
		subjectService.updateState(id, activeEnum);
		return "\"success\"";
	}

	@RequestMapping(value = "/admin/subjects/{id}/solutions", method = RequestMethod.GET)
	@ResponseBody
	public List<SolutionJson> solution(@PathVariable("id") Long id) {
		Subject s = subjectService.findOne(id);
		return s.getSolutions().stream().map(p -> new SolutionJson(p))
				.collect(Collectors.toList());
	}
}