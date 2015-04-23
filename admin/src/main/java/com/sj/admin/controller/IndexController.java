package com.sj.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping(value = {"/index","/"})
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/login")
	public String login(){
		return "login";
	}
}
