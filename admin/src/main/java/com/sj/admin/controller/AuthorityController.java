package com.sj.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.repository.model.SiteRoleJson;
import com.sj.repository.service.SiteRoleService;

@Controller
@RequestMapping("/admin")
public class AuthorityController {
	@Autowired
	private SiteRoleService service;

	@RequestMapping(value = "/authorities", method = RequestMethod.GET)
	@ResponseBody
	public List<SiteRoleJson> list(
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size) {
		return service.findAll(new PageRequest(page - 1, size));
	}
}
