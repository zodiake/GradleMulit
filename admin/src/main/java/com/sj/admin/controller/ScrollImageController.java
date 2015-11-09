package com.sj.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.ScrollImage;
import com.sj.repository.service.ScrollImageService;

@Controller
public class ScrollImageController extends UploadController {
	@Autowired
	private ScrollImageService service;

	@RequestMapping(value = "/admin/scrollImages", method = RequestMethod.GET)
	@ResponseBody
	public List<ScrollImage> list() {
		List<ScrollImage> lists = service.findAllJson();
		return lists;
	}

	@RequestMapping(value = "/admin/scrollImages/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String save(ScrollImage image, @PathVariable("id") Long id) {
		service.update(id, image);
		return "";
	}
	
	@RequestMapping(value = "/admin/scrollImages",method = RequestMethod.POST)
	@ResponseBody
	public String add(ScrollImage image){
		service.save(image);
		return "";
	}
	@RequestMapping(value = "/admin/scrollImages/{id}/state",method = RequestMethod.POST)
	@ResponseBody
	public String updateState(@PathVariable("id")Long id){
		service.updateState(id);
		return "";
	}
}