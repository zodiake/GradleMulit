package com.sj.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.CategoryJson;
import com.sj.repository.service.PreferProductService;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.ProductService;
import com.sj.web.exception.CategoryNotFoundException;
import com.sj.web.security.SiteUserContext;

@Controller
public class ProductCategoryController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService pcService;
	@Autowired
	private PreferProductService preferProductService;
	@Autowired
	private SiteUserContext userContext;

	@RequestMapping(value = "/productCategory/{id}", method = RequestMethod.GET)
	public String findByParent(@PathVariable("id") Long id, Model uiModel) {
		ProductCategory pc = pcService.findByIdAndParent(id);
		if (pc == null)
			throw new CategoryNotFoundException();
		List<ProductCategory> categories = pcService.findByParentAndActivate(pc, ActivateEnum.ACTIVATE);
		uiModel.addAttribute("pc", pc);
		uiModel.addAttribute("categories", categories);
		return "productcategory/productCategorys";
	}
	
	@RequestMapping(value = "/ajaxProductCategory/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<CategoryJson> ajaxFindByParent(@PathVariable("id")Long id){
		ProductCategory pc = pcService.findOneActivate(id);
		if (pc == null)
			throw new CategoryNotFoundException();
		List<CategoryJson> categories = pcService.ajaxFindByParent(pc);
		return categories;
	}

}
