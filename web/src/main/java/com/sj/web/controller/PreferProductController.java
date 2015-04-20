package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.repository.service.PreferProductService;
import com.sj.web.security.UserContext;

@Controller
public class PreferProductController {
	@Autowired
	private PreferProductService preferProductService;
	@Autowired
	private UserContext userContext;
	
	@RequestMapping(value="/preferedProduct/{id}",method=RequestMethod.POST)
	@ResponseBody
	public String addPrefer(@PathVariable("id")String id){
		
		return "success";
	}

}
