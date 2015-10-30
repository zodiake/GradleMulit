package com.sj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.ProductDisplayJson;
import com.sj.repository.service.ProductDisplayService;

@Controller
public class ProductDisplayController {

	@Autowired
	private ProductDisplayService productDisplayService;

	@RequestMapping(value = "/admin/productDisplays", method = RequestMethod.GET)
	@ResponseBody
	public Page<ProductDisplayJson> findAll(
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size) {
		return productDisplayService
				.findAllJson(new PageRequest(page - 1, size));
	}

	@RequestMapping(value = "/admin/productDisplays/{id}/state", method = RequestMethod.POST)
	@ResponseBody
	public String updateState(@PathVariable("id") Long id,
			@RequestParam("state") ActivateEnum state) {
		String result = productDisplayService.updateState(id, state);
		return "\""+result+"\"";
	}
	@RequestMapping(value = "/admin/productDisplays/productId/{productId}",method =RequestMethod.POST)
	@ResponseBody
	public String addProductDisplay(@PathVariable("productId")Long productId){
		String result = productDisplayService.save(productId);
		return "\""+result+"\"";
	}
}
