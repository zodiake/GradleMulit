package com.sj.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.SiteUser;
import com.sj.repository.service.SiteUserService;
import com.sj.web.exception.UserNotFoundException;
import com.sj.web.security.SiteUserContext;

@Controller
public class SiteUserController {
	@Autowired
	private SiteUserService siteUserService;
	@Autowired
	private SiteUserContext userContext;

	private final String SITEUSER = "";

	@RequestMapping(value = "/currentUsers", method = RequestMethod.GET)
	public String findCurrentUser(Model uiModel) {
		SiteUser user = userContext.getCurrentUser();
		if (user == null)
			throw new UserNotFoundException();
		uiModel.addAttribute("siteUser", user);
		return SITEUSER;
	}
	
}
