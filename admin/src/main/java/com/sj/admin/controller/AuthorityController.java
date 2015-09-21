package com.sj.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.SiteRoleJson;
import com.sj.repository.service.SiteRoleService;

@Controller
@RequestMapping("/admin")
public class AuthorityController {
	@Autowired
	private SiteRoleService service;

	@RequestMapping(value = "/authorities", method = RequestMethod.GET)
	@ResponseBody
	public List<SiteRoleJson> list(HttpServletRequest request) {
		String active = request.getParameter("active");
		ActivateEnum activeEnum = ActivateEnum.fromString(active);
		return service.findAllJson(activeEnum);
	}

	@RequestMapping(value = "/authorities/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SiteRoleJson findOne(@PathVariable("id") Long id) {
		return service.findOneJson(id);
	}
}
