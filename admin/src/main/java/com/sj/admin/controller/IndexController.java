package com.sj.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sj.model.model.SiteUser;

@Controller
public class IndexController {
	@RequestMapping(value = { "/index", "/" })
	public String index(Model model) {
		SiteUser user = new SiteUser();
		model.addAttribute("user", user);
		return "login";
	}
}
