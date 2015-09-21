package com.sj.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.repository.model.SiteMenuJson;
import com.sj.repository.service.MenuService;

@Controller
@RequestMapping("/admin")
public class MenuController {
	@Autowired
	private MenuService service;

	@RequestMapping(value = "/menus")
	@ResponseBody
	public List<SiteMenuJson> lists() {
		return service.findAll();
	}

}
