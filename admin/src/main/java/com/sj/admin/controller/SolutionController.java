package com.sj.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.Solution;
import com.sj.model.model.Subject;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.SolutionJson;
import com.sj.repository.service.SolutionService;

@Controller
@RequestMapping(value = "/admin")
public class SolutionController {
	@Autowired
	private SolutionService solutionService;

	@RequestMapping(value = "/solutions/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String updateByName(@PathVariable("id") Long id, Solution solution) {
		solutionService.updateName(id, solution.getName());
		return "{\"id\":\"" + id + "\"}";
	}

	@RequestMapping(value = "/solutions/{id}/state", method = RequestMethod.POST)
	@ResponseBody
	public String updateState(HttpServletRequest request,
			@PathVariable("id") Long id) {
		String active = request.getParameter("activate");
		ActivateEnum activeEnum = ActivateEnum.fromString(active);
		solutionService.updateState(id, activeEnum);
		return "{\"status\":\"success\"}";
	}

	@RequestMapping(value = "/subjects/{id}/solutions", method = RequestMethod.POST)
	@ResponseBody
	public String addSolution(@PathVariable("id") Long id, Solution solution) {
		solution.setSubject(new Subject(id));
		Solution solu = solutionService.save(solution);
		return "{\"id\":\"" + solu.getId() + "\"}";
	}

	@RequestMapping(value = "/solutions", method = RequestMethod.GET)
	@ResponseBody
	public List<SolutionJson> findAll() {
		return solutionService.findAll();
	}

}
