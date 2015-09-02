package com.sj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.SiteUser;
import com.sj.repository.service.ProviderService;
import com.sj.repository.service.SiteUserService;

@Controller
public class AdminUserController {
	@Autowired
	private SiteUserService userService;

	@Autowired
	private ProviderService providerService;

	@RequestMapping(value = "/users")
	public String userList(Model uiModel,
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size,
			@RequestParam(value = "state", defaultValue = "-1") int state) {
		PageRequest pageRequest = new PageRequest(page - 1, size,
				Direction.ASC, "createdTime");
		Page<SiteUser> userList = userService.findAll(pageRequest);
		uiModel.addAttribute("lists", userList);
		return "index";
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