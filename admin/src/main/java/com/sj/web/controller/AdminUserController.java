package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.model.model.SiteUser;
import com.sj.repository.service.SiteUserService;

@Controller
public class AdminUserController extends BaseController{
	@Autowired
	private SiteUserService userService;

	@RequestMapping(value = "/users")
	public String userList(Model uiModel,
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size) {
		PageRequest pageRequest = new PageRequest(page-1, size, Direction.ASC,
				"createdTime");
		Page<SiteUser> userList = userService.findAll(pageRequest);
		uiModel.addAttribute("lists", userList);
		uiModel.addAttribute("page", caculatePage(userList));
		return "index";
	}
}
