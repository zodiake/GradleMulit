package com.sj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.SiteUser;
import com.sj.repository.model.SiteUserJson;
import com.sj.repository.service.ProviderService;
import com.sj.repository.service.SiteUserService;

@Controller
public class AdminUserController {
	@Autowired
	private SiteUserService userService;

	@Autowired
	private ProviderService providerService;

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	@ResponseBody
	public Page<SiteUserJson> userList(
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size) {
		Page<SiteUserJson> pages = userService.findBySiteAuthority(
				"ROLE_ADMIN", new PageRequest(page - 1, size));
		return pages;
	}

	@RequestMapping(value = "/admin/users/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String save(@PathVariable("id") Long id, SiteUser user) {
		SiteUser u = userService.save(user);
		return "{\"id\":\"" + u.getId() + "\"}";
	}

	@RequestMapping(value = "/checkUsers", method = RequestMethod.POST)
	@ResponseBody
	public String checkUser(
			@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "state", required = true) int state,
			Model uiModel) {
		userService.updateEnabledById(id, state);
		return "success";
	}
}